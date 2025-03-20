package com.quizapp.QuizService.service;


import com.quizapp.QuizService.dto.QuizParamDto;
import com.quizapp.QuizService.exception.EmailSendingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void notifyContestants(QuizParamDto quizDto){

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("yj26102003@gmail.com");
            simpleMailMessage.setTo(quizDto.getContestants().toArray(new String[0]));
            simpleMailMessage.setSubject("Invitation for " + quizDto.getTitle() + " Quiz");
            simpleMailMessage.setText("You are Invited to participate in Quiz starting on:" + quizDto.getStartTime() + " and ending on:" + quizDto.getEndTime());
            javaMailSender.send(simpleMailMessage);
        }
        catch (Exception e){
            throw new EmailSendingException("Unable to send the Email");
        }
    }

}
