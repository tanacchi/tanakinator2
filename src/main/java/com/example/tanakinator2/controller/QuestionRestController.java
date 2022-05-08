package com.example.tanakinator2.controller;

import com.example.tanakinator2.domain.Question;
import com.example.tanakinator2.service.Tanakinator2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/question")
public class QuestionRestController {
    private static final Logger logger = LoggerFactory.getLogger(Tanakinator2RestController.class);
    private final Tanakinator2Service service;

    public QuestionRestController(Tanakinator2Service service) {
        this.service = service;
    }

    @GetMapping(path = "{questionId}", produces = "application/json")
    public Question questionDetail(@PathVariable int questionId) {
        return service.getQuestion(questionId);
    }

    @PostMapping(path = "add", produces = "application/json")
    public void questionPost(@RequestBody Question question) {
        service.addQuestion(question);
    }

    @PatchMapping(path = "{questionId}", produces = "application/json")
    public void questionPatch(@PathVariable int questionId, @RequestBody Question question) {
        question.setQuestionId(questionId);
        service.setQuestion(question);
    }

    @DeleteMapping(path = "{questionId}")
    public void questionDelete(@PathVariable int questionId) {
        service.removeQuestion(questionId);
    }
}
