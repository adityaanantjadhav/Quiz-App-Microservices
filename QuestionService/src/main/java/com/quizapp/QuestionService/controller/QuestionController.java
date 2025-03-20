package com.quizapp.QuestionService.controller;


import com.quizapp.QuestionService.model.Question;
import com.quizapp.QuestionService.dto.QuestionWrapper;
import com.quizapp.QuestionService.dto.QuizDto;
import com.quizapp.QuestionService.dto.Response;
import com.quizapp.QuestionService.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {


    @Autowired
    private QuestionService questionService;
    @GetMapping("")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return new ResponseEntity<List<Question>>(questionService.getAllQuestions(), HttpStatus.OK);
    }


    @GetMapping("/category/{topic}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable("topic") String topic){
        return new ResponseEntity<>(questionService.getQuestionByCategory(topic),HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<?> addQuestion(@RequestBody Question question, @RequestPart MultipartFile file) throws IOException {
        System.out.println("Question:"+question.getQuestionTitle());
        return new ResponseEntity<Question>(questionService.addQuestion(question,file),HttpStatus.CREATED);
    }

    @PostMapping("/generate-quiz")
    public ResponseEntity<?> generateQuiz(@RequestBody QuizDto quizDto){
        return new ResponseEntity<List<Integer>>(questionService.generateQuiz(quizDto),HttpStatus.CREATED);

    }

    @PostMapping("/get-quiz")
    public ResponseEntity<?> getQuiz(@RequestBody List<Integer> questionIds){
        return new ResponseEntity<List<QuestionWrapper>>(questionService.getQuiz(questionIds),HttpStatus.OK);
    }


    @PostMapping("/score")
    public ResponseEntity<?> getScore(@RequestBody List<Response> responses){
        return new ResponseEntity<Integer>(questionService.getScore(responses),HttpStatus.OK);
    }



}
