package com.quizapp.QuestionService.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;

    private String userId;

    private String questionTitle;
    private String category;


    private String imagePath;

    private String option1;
    private String option2;
    private String option3;
    private String option4;


    private String rightAnswer;
    private String difficultyLevel;


    @Column(nullable = false)
    private boolean isPrivate = false;

}
