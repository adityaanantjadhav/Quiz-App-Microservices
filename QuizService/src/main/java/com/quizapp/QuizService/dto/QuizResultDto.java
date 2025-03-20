package com.quizapp.QuizService.dto;

import com.quizapp.QuizService.model.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class QuizResultDto {


    private int quizId;
    private String creatorId;
    private String title;
    private String category;
    private String difficultyLevel;

    private List<Result> resultList=new ArrayList<>();



    public QuizResultDto(int quizId, String creatorId, String title, String category, String difficultyLevel, Result result) {
        this.quizId = quizId;
        this.creatorId = creatorId;
        this.title = title;
        this.category = category;
        this.difficultyLevel = difficultyLevel;
        this.resultList.add(result);
    }

}
