package com.quizapp.UserService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizResultDto {

    private int quizId;
    private String creatorId;
    private String title;
    private String category;
    private String difficultyLevel;

    private List<Result> resultList=new ArrayList<>();

}
