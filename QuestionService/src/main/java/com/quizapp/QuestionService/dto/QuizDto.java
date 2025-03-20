package com.quizapp.QuestionService.dto;

import lombok.Data;

@Data
public class QuizDto {
    private String title;
    private int noOfQuestions;
    private String category;
    private String difficultyLevel;

    private boolean includeGeneral;
}
