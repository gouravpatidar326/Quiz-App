package com.gourav.quizBackend.service;

import com.gourav.quizBackend.entity.QuizQuestion;
import com.gourav.quizBackend.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    public QuizQuestion saveQuestion(QuizQuestion question) {
        return questionRepo.save(question);
    }

    public List<QuizQuestion> getQuestions(String topic){
        return questionRepo.findByTopic(topic);
    }

    public List<QuizQuestion> saveAllQuestions(List<QuizQuestion> questions) {
        return questionRepo.saveAll(questions);
    }

}
