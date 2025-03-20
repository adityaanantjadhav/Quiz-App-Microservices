package com.quizapp.QuizService.repository;

import com.quizapp.QuizService.dto.QuizResultDto;
import com.quizapp.QuizService.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepo extends JpaRepository<Result,Integer> {
    List<QuizResultDto> findByUserId(String userId);
}
