package com.quizapp.QuizService.dto;


import com.quizapp.QuizService.model.Quiz;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizResponseDto {


    private Integer quizId;
    private String creatorId;
    private String title;
    private String category;
    private String difficultyLevel;

    List<QuestionWrapper> questions;


    public QuizResponseDto(Quiz quiz,List<QuestionWrapper> list){
        this.quizId=quiz.getId();
        this.creatorId= quiz.getCreatorId();
        this.title=quiz.getTitle();
        this.category= quiz.getCategory();
        this.difficultyLevel=quiz.getDifficultyLevel();
        this.questions=list;
    }


}
