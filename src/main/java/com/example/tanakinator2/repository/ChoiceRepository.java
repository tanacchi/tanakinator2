package com.example.tanakinator2.repository;

import com.example.tanakinator2.domain.Choice;

import java.util.List;

public interface ChoiceRepository {
    List<Choice> findChoices(String choiceName);
    Choice getChoice(int choiceId);
}
