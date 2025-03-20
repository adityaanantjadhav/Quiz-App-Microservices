package com.quizapp.QuizService.service;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.Authentication;

import com.quizapp.QuizService.dto.*;
import com.quizapp.QuizService.exception.NotAuthorizedException;
import com.quizapp.QuizService.exception.ResouceNotFoundException;
import com.quizapp.QuizService.feign.QuestionServiceInterface;
import com.quizapp.QuizService.model.Quiz;
import com.quizapp.QuizService.model.Result;
import com.quizapp.QuizService.repository.QuizRepo;
import com.quizapp.QuizService.repository.ResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class QuizService {


    @Autowired
    private QuestionServiceInterface questionService;


    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private ResultRepo resultRepo;


    @Autowired
    private EmailService emailService;


    private String getCurrentUserEmail(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null && auth.getPrincipal() instanceof Jwt){
            Jwt jwt=(Jwt) auth.getPrincipal();
            return jwt.getClaimAsString("email");

        }
        return null;
    }

    public Quiz createQuiz(QuizParamDto quizDto) {
        Quiz quiz=new Quiz();

        quiz.setTitle(quizDto.getTitle());

        List<Integer>list=questionService.generateQuiz(quizDto).getBody();
        quiz.setQuestions(list);
        if(quizDto.getCategory()==null)
            quizDto.setCategory("General");

        quiz.setCategory(quizDto.getCategory());

        if(quizDto.getDifficultyLevel()==null)
            quizDto.setDifficultyLevel("General");

        quiz.setDifficultyLevel(quizDto.getDifficultyLevel());

        quiz.setCreatorId(getCurrentUserEmail());

        quiz.setStartTime(quizDto.getStartTime());

        quiz.setEndTime(quizDto.getEndTime());

        quiz.setUsersPermittedToSolve(quizDto.getContestants());

        if(quizDto.isNotifyContestant()){
            emailService.notifyContestants(quizDto);
        }

        return quizRepo.save(quiz);

    }

    public QuizResponseDto solveQuiz(Integer quizId) {
        Quiz quiz=quizRepo.findById(quizId).orElseThrow(()->new ResouceNotFoundException("Sorry!!, Invalid Quiz Id"));


        String userId=getCurrentUserEmail();

        List<String> permittedUsers=quiz.getUsersPermittedToSolve();
        if(!permittedUsers.isEmpty() && !permittedUsers.contains(userId)){
            throw new NotAuthorizedException("You are not permitted to solve this Quiz "+userId);
        }


        LocalDateTime currentTime=LocalDateTime.now();
        if(quiz.getStartTime()!=null && currentTime.isBefore(quiz.getStartTime())){
            throw new NotAuthorizedException("Quiz hasn't started yet");
        }

        if(quiz.getEndTime()!=null && currentTime.isAfter(quiz.getEndTime())){
            throw new NotAuthorizedException("Quiz has ended already");
        }

        List<Integer> listOfQuestionIds= quiz.getQuestions();
        List<QuestionWrapper> listOfQuestionWrappers=questionService.getQuiz(listOfQuestionIds).getBody();


        QuizResponseDto quizResponseDto=new QuizResponseDto(quiz,listOfQuestionWrappers);
        return quizResponseDto;
    }

    public Result calculateResult(int quizId, List<Response> responses) {
        String userId=getCurrentUserEmail();

        Integer scores=questionService.getScore(responses).getBody();
        Quiz q=quizRepo.findById(quizId).orElseThrow(()->new ResouceNotFoundException("Quiz Not found!!. Mistake in quizId"));
        List<Result> list=q.getResult();
        Result newResult=new Result();
        newResult.setScore(scores);
        newResult.setUserId(userId);
        newResult.setQuizSubmitTime(LocalDateTime.now());
        int index=0;
        if(list.isEmpty()) {
            newResult.setRank(1);
            list.add(newResult);
        }
        else if(list.get(0).getScore()<scores) {
            newResult.setRank(1);
            list.add(0, newResult);
        }
        else{
            index=Collections.binarySearch(list,newResult,(a,b)->Integer.compare(a.getScore(),b.getScore()));
            if(index>=0) {
                for (; index < list.size(); index++) {
                    if (list.get(index).getScore() < scores) break;
                }
            }
            else {
                index=(index+1)*-1;
            }

            newResult.setRank(index+1);
            list.add(index,newResult);
        }

        for(;index<list.size();index++){
            Result result=list.get(index);
            result.setRank(index+1);
            list.set(index,result);
        }

        q.setResult(list);
        quizRepo.save(q);
        return newResult;
    }


    //This function returns Attempted quizes if isAttempted is true and Created Quizes if isAttempted is false
    public List<QuizResultDto> getQuizes(boolean isAttempted) {
        List<Quiz> listOfQuiz;


        String userId=getCurrentUserEmail();

        List<QuizResultDto> quizResultList;
        if(isAttempted) {
            quizResultList = quizRepo.getAttemptedQuizzes(userId);
        }
        else {
            listOfQuiz = quizRepo.findByCreatorId(userId);

            quizResultList = listOfQuiz.stream().map((q) -> {
                QuizResultDto qr = new QuizResultDto();
                qr.setCategory(q.getCategory());
                qr.setTitle(q.getTitle());
                qr.setResultList(q.getResult());
                qr.setCreatorId(q.getCreatorId());
                qr.setQuizId(q.getId());
                qr.setDifficultyLevel(q.getDifficultyLevel());
                return qr;
            }).toList();
        }

        return quizResultList;
    }
}

