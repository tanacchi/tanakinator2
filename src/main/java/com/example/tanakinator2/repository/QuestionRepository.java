package com.example.tanakinator2.repository;

import com.example.tanakinator2.domain.Question;

import java.util.List;

public interface QuestionRepository {
    Question getQuestionById(int questionId);
    List<Question> getQuestionsById(List<Integer> questionIds);
    Question lock(int questionId);
    void insert(Question question);
    void update(Question question);
    void delete(Question question);
}
