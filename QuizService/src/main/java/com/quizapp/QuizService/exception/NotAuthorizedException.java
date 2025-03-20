package com.quizapp.QuizService.exception;

import javax.management.RuntimeMBeanException;

public class NotAuthorizedException extends RuntimeException {


    public NotAuthorizedException() {
        super("Not authorized to do this");
    }

    public NotAuthorizedException(String message){
        super(message);
    }
}
