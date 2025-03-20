package com.quizapp.UserService.feign;


import com.quizapp.UserService.dto.QuizResultDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "QUIZSERVICE", path = "/quiz")
public interface QuizServiceImple {

    @PostMapping("/attemptedQuiz")
    public ResponseEntity<List<QuizResultDto>> getAttemptedQuiz();


    @PostMapping("/organizedQuiz")
    public ResponseEntity<List<QuizResultDto>> getOrganisedQuiz();

}
