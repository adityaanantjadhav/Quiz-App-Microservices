package com.quizapp.QuizService.exception;


import com.quizapp.QuizService.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<ExceptionResponse> notAuthorizedExceptionHandler(Exception e){
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage(), HttpStatus.UNAUTHORIZED),HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ResouceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resouceNotFoundException(Exception e){
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND),HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> runtimeExceptionHandler(Exception e){
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR),HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EmailSendingException.class)
    public ResponseEntity<ExceptionResponse> emailSendingExceptionHandler(Exception e){
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
