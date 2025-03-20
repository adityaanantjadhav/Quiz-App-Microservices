package com.quizapp.QuestionService.service;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.Authentication;

import com.quizapp.QuestionService.model.Question;
import com.quizapp.QuestionService.dto.QuestionWrapper;
import com.quizapp.QuestionService.dto.QuizDto;
import com.quizapp.QuestionService.dto.Response;
import com.quizapp.QuestionService.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepo;

    @Value("${app.upload.dir}")
    private String uploadDir;


    public List<Question> getAllQuestions(){
        return questionRepo.findAll();
    }

    public List<Question> getQuestionByCategory(String topic) {
        return questionRepo.findAllByCategory(topic);

    }

    public Question addQuestion(Question question, MultipartFile file) throws IOException {

        Question newQuestion=questionRepo.save(question);

        String name=file.getOriginalFilename();
        int index=name.lastIndexOf('.');
        String fileExtension=name.substring(index);


        File directory=new File(uploadDir);
        if(!directory.exists()){
            directory.mkdirs();
        }


        name=newQuestion.getId()+fileExtension;
        File destinationFile = new File(directory + File.separator + name);
        file.transferTo(destinationFile);


        newQuestion.setImagePath(uploadDir+"/"+name);
        return questionRepo.save(newQuestion);
    }



    //This returns the email address ie. username of current user
    private String getCurrentUserEmail(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null && auth.getPrincipal() instanceof Jwt){
            Jwt jwt= (Jwt) auth.getPrincipal();

            return jwt.getClaimAsString("email");
        }
        return null;
    }

    public List<Integer> generateQuiz(QuizDto quizDto){

        String userId=getCurrentUserEmail();
        if(quizDto.isIncludeGeneral()){
            userId=null;
        }
        return questionRepo.createQuiz(quizDto.getCategory(),quizDto.getDifficultyLevel(),quizDto.getNoOfQuestions(),userId);
    }


    public List<QuestionWrapper> getQuiz(List<Integer> questionIds){
        List<Question>questionList=questionRepo.findAllById(questionIds);

        List<QuestionWrapper> questionWrapperList=questionList.stream().map((q)-> new QuestionWrapper(q.getId(),
                q.getQuestionTitle(),
                q.getImagePath(),
                q.getOption1(),
                q.getOption2(),
                q.getOption3(),
                q.getOption4())
        ).toList();

        return  questionWrapperList;
    }


    public Integer getScore(List<Response> responses) {
        int marks=0;
        for(Response r:responses){
            Optional<Question> question=questionRepo.findById(r.getId());
            if(question.isEmpty()) continue;
            if(r.getResponse().equals(question.get().getRightAnswer())){
                marks++;
            }
        }
        return marks;
    }
}
