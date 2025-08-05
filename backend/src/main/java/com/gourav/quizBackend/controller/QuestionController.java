package com.gourav.quizBackend.controller;

import com.gourav.quizBackend.entity.QuizQuestion;
import com.gourav.quizBackend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("api")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/getQuestions/{topic}")
    public List<QuizQuestion> getQuestions(@PathVariable String topic){
        return questionService.getQuestions(topic);
    }

    @PostMapping(value = "/save", consumes = "application/json", produces = "application/json")
    public QuizQuestion saveQuestion(@RequestBody QuizQuestion question){
        return questionService.saveQuestion(question);
    }

    @PostMapping(value = "/saveAll", consumes = "application/json", produces = "application/json")
    public List<QuizQuestion> saveAllQuestions(@RequestBody List<QuizQuestion> questions) {
        return questionService.saveAllQuestions(questions);
    }
}

