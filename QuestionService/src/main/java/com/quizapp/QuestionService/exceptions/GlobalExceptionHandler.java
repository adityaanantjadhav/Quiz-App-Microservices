package com.quizapp.QuestionService.exceptions;


import com.quizapp.QuestionService.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(IOException.class)
    public ResponseEntity<ExceptionResponse> iOExceptionHandler(Exception e){
        return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
    }
}
