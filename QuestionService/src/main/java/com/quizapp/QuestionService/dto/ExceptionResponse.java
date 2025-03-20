package com.quizapp.QuestionService.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.net.http.HttpResponse;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    private String message;
    private HttpStatus status;

}
