package com.example.tanakinator2.controller;

import com.example.tanakinator2.domain.Choice;
import com.example.tanakinator2.service.Tanakinator2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/choice")
public class ChoiceRestController {
    private static final Logger logger = LoggerFactory.getLogger(Tanakinator2RestController.class);
    private final Tanakinator2Service service;

    public ChoiceRestController(Tanakinator2Service service) {
        this.service = service;
    }

    @GetMapping(path = "", produces = "application/json")
    public List<Choice> choiceDetail() {
        return service.getAllChoices();
    }

    @GetMapping(path = "{choiceId}", produces = "application/json")
    public Choice choiceDetail(@PathVariable int choiceId) {
        return service.getChoice(choiceId);
    }
}
