package com.quizapp.UserService.controller;


import com.quizapp.UserService.dto.User;
import com.quizapp.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {


    @Autowired
    private UserService userService;

//    @PostMapping()
//    public ResponseEntity<User> createUser(@RequestBody User user){
//        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
//    }

    @GetMapping()
    public ResponseEntity<User> getUser(){
        return new ResponseEntity<>(userService.getUser(), HttpStatus.OK);
    }

//
//    @GetMapping("/{userId}/solveQuiz/{quizId}")
//    public ResponseEntity<?> solveQuiz(@PathVariable("userId") String userId,@PathVariable("quizId") int quizId){
//        return new ResponseEntity<QuizResponseDto>(userService.solveQuiz(userId,quizId),HttpStatus.OK);
//    }
//
//
//    @GetMapping("/{userId}/attemptedQuiz")
//    public ResponseEntity<?> getAttemptedQuiz(@PathVariable("userId") String userId){
//        return new ResponseEntity<List<QuizResultDto>>(userService.getAttemptedQuiz(userId), HttpStatus.OK);
//    }
//    @GetMapping("/{userId}/organizedQuiz")
//    public ResponseEntity<?> getOrganisedQuiz(@PathVariable("userId") String userId){
//         return new ResponseEntity<List<QuizResultDto>>(userService.getOrganisedQuiz(userId),HttpStatus.OK);
//    }

}

