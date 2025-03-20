package com.quizapp.QuizService.feign;


import com.quizapp.QuizService.dto.QuestionWrapper;
import com.quizapp.QuizService.dto.QuizParamDto;
import com.quizapp.QuizService.dto.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "QUESTIONSERVICE", path = "/question")
public interface QuestionServiceInterface {

    @PostMapping("/generate-quiz")
    public ResponseEntity<List<Integer>> generateQuiz(@RequestBody QuizParamDto quizParamDto) ;

    @PostMapping("/get-quiz")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@RequestBody List<Integer> questionIds);


    @PostMapping("/score")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
