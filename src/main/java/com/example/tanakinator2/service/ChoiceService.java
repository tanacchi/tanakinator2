package com.example.tanakinator2.service;

import com.example.tanakinator2.domain.Choice;

import java.util.List;

public interface ChoiceService {
    Choice getChoice(int choiceId);
    List<Choice> getAllChoices();
}
