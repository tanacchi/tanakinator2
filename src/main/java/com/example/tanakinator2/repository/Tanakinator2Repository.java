package com.example.tanakinator2.repository;

import com.example.tanakinator2.domain.Choice;

import java.util.List;

public interface Tanakinator2Repository {
    List<Choice> find(String choiceName);
    Choice getChoice(int choiceId);
}
