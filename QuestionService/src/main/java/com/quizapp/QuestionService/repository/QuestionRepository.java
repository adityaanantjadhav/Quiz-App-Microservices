package com.quizapp.QuestionService.repository;

import com.quizapp.QuestionService.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    List<Question> findAllByCategory(String topic);



    @Query(value = "SELECT id FROM question e "
            +"WHERE (:category IS NULL OR e.category= :category) "
            +"AND (:difficultyLevel IS NULL OR e.difficulty_level= :difficultyLevel) "
            +"AND (:userId IS NULL OR e.user_id= :userId) "
            +"ORDER BY RANDOM() LIMIT :noOfQuestions", nativeQuery = true
    )
    List<Integer> createQuiz(@Param("category") String category,@Param("difficultyLevel") String difficultyLevel,@Param("noOfQuestions") int noOfQuestions,@Param("userId") String userId);
}
