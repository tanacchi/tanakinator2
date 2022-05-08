package com.example.tanakinator2.service;

import com.example.tanakinator2.domain.Question;

public interface QuestionService {
    Question getQuestion(int questionId);
    void addQuestion(Question question);
    void setQuestion(Question question);
    void removeQuestion(int questionId);
}
