package com.example.tanakinator2.controller;

import com.example.tanakinator2.domain.Choice;
import com.example.tanakinator2.domain.Question;
import com.example.tanakinator2.service.ChoiceService;
import com.example.tanakinator2.service.Tanakinator2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api")
public class Tanakinator2RestController {
    private static final Logger logger = LoggerFactory.getLogger(Tanakinator2RestController.class);
    private final Tanakinator2Service service;

    public Tanakinator2RestController(Tanakinator2Service service) {
        this.service = service;
    }

    @GetMapping(path = "", produces = "application/json")
    public String home() {
        return "Hello";
    }

    @GetMapping(path = "choice", produces = "application/json")
    public List<Choice> choiceDetail() {
        return service.getAllChoices();
    }

    @GetMapping(path = "choice/{choiceId}", produces = "application/json")
    public Choice choiceDetail(@PathVariable int choiceId) {
        return service.getChoice(choiceId);
    }

    @GetMapping(path = "question/{questionId}", produces = "application/json")
    public Question questionDetail(@PathVariable int questionId) {
        return service.getQuestion(questionId);
    }

    @PostMapping(path = "question/add", produces = "application/json")
    public void questionPost(@RequestBody Question question) {
        service.addQuestion(question);
    }

    @PatchMapping(path = "question/{questionId}", produces = "application/json")
    public void questionPatch(@PathVariable int questionId, @RequestBody Question question) {
        question.setQuestionId(questionId);
        service.setQuestion(question);
    }

    @DeleteMapping(path = "question/{questionId}")
    public void questionDelete(@PathVariable int questionId) {
        service.removeQuestion(questionId);
    }
}
