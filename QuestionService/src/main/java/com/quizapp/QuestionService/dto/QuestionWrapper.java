package com.quizapp.QuestionService.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionWrapper {
    private int id;

    private String questionTitle;

    private String imagePath;

    private String option1;
    private String option2;
    private String option3;
    private String option4;

}
