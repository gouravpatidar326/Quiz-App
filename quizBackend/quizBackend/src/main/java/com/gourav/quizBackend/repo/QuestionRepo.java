package com.gourav.quizBackend.repo;

import com.gourav.quizBackend.entity.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<QuizQuestion,Long> {

    @Query(value = "SELECT * FROM quiz_question WHERE topic = :topic ORDER BY RAND() LIMIT 5", nativeQuery = true)
    List<QuizQuestion> findByTopic(String topic);
}
