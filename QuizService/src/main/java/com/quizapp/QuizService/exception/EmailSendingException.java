package com.quizapp.QuizService.exception;

public class EmailSendingException extends RuntimeException{

    public EmailSendingException(){
        super();
    }

    public EmailSendingException(String message){
        super(message);
    }
}
