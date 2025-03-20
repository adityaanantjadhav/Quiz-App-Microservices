package com.quizapp.UserService.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class User {

    private String userId;
    private String name;

    private List<QuizResultDto> quizesCreated=new ArrayList<>();

    private List<QuizResultDto> quizesAttempted=new ArrayList<>();
}
