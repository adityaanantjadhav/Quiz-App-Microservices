package com.quizapp.QuizService.controller;


import com.quizapp.QuizService.dto.*;
import com.quizapp.QuizService.model.Quiz;
import com.quizapp.QuizService.model.Result;
import com.quizapp.QuizService.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {


    @Autowired
    private QuizService quizService;



    //We can use @RequestBody Map<String,String> instead of all these request params as well. For that we need to send data in JSON format
//   I decided to use a class instead of using RequestParams public ResponseEntity<?> createQuiz(@RequestParam("category") String category,@RequestParam("difficultyLevel") String difficultyLevel,@RequestParam(value="noOfQuestions", defaultValue = "10") int noOfQuestions,@RequestParam("title") String title){
    @PostMapping("/create")
    public ResponseEntity<?> createQuiz(@RequestBody QuizParamDto quizDto){
        return new ResponseEntity<Quiz>(quizService.createQuiz(quizDto), HttpStatus.CREATED);
    }

    @GetMapping("/get-quiz/{quizId}")
    public ResponseEntity<?> getQuiz(@PathVariable("quizId") Integer quizId){
        return new ResponseEntity<QuizResponseDto>(quizService.solveQuiz(quizId),HttpStatus.OK);
    }


    @PostMapping("/submit/{quizId}")
    public ResponseEntity<?> submitQuiz( @PathVariable("quizId") int quizId,@RequestBody List<Response> responses){
        return new ResponseEntity<Result>(quizService.calculateResult(quizId,responses),HttpStatus.OK);
    }


    @PostMapping("/attempted")
    public ResponseEntity<?> getAttemptedQuiz(){
        return new ResponseEntity<List<QuizResultDto>>(quizService.getQuizes(true),HttpStatus.OK);
    }


    @PostMapping("/organized")
    public ResponseEntity<?> getOrganisedQuiz(){
        return new ResponseEntity<List<QuizResultDto>>(quizService.getQuizes(false),HttpStatus.OK);
    }
}
