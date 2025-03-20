package com.quizapp.QuizService.repository;


import com.quizapp.QuizService.dto.QuizResultDto;
import com.quizapp.QuizService.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuizRepo extends JpaRepository<Quiz,Integer> {


    @Query("SELECT new com.quizapp.QuizService.dto.QuizResultDto(q.id, q.creatorId, q.title, q.category, q.difficultyLevel, r) " +
            "FROM Quiz q JOIN q.result r WHERE r.userId = :userId")
    List<QuizResultDto> getAttemptedQuizzes(@Param("userId") String userId);

    public List<Quiz> findByCreatorId(String userId);
}
