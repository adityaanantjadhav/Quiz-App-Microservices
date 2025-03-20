package com.quizapp.UserService.service;

import com.quizapp.UserService.feign.QuizServiceImple;
import com.quizapp.UserService.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;


@Service
public class UserService {



    @Autowired
    private QuizServiceImple feignQuizService;


//    public User createUser(User user) {
//        return userRepo.save(user);
//
//    }

    private String username=null;
    private String getCurrentUserEmail(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null && auth.getPrincipal() instanceof Jwt){
            Jwt jwt=(Jwt) auth.getPrincipal();
            username=jwt.getClaimAsString("preferred_username");
            return jwt.getClaimAsString("email");
        }
        return null;
    }



    public User getUser() {
        User user=new User();
        String userId=getCurrentUserEmail();
        user.setUserId(userId);
        user.setName(username);
        user.setQuizesAttempted(feignQuizService.getAttemptedQuiz().getBody());
        user.setQuizesCreated(feignQuizService.getOrganisedQuiz().getBody());
        return user;

    }







    //Commented below because instead of using User Service to call all the apis
    //I will expose all the service's endpoints to reduce interdependency(tight coupling)

//    public List<QuizResultDto> getAttemptedQuiz(String userId) {
//
//        //Feign client to QuizService
//        List<QuizResultDto> quizResultDtoList=feignQuizService.getAttemptedQuiz(userId).getBody();
//
//        return quizResultDtoList;
//
//
//    }
//
//    public List<QuizResultDto> getOrganisedQuiz(String userId){
//        List<QuizResultDto> quizResultDtoList=feignQuizService.getOrganisedQuiz(userId).getBody();
//
//        return quizResultDtoList;
//    }
//
//
//    public QuizResponseDto solveQuiz(String userId, int quizId){
//
//            QuizResponseDto quizResponseDto = feignQuizService.getQuiz(userId,quizId).getBody();
//            return quizResponseDto;
//    }



}
