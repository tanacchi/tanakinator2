package com.example.tanakinator2.repository;

import com.example.tanakinator2.domain.Question;

import java.util.List;

public interface QuestionRepository {
    Question getQuestionById(int questionId);
    List<Question> getQuestionsById(List<Integer> questionIds);
    Question lockQuestion(int questionId);
    void insertQuestion(Question question);
    void updateQuestion(Question question);
    void deleteQuestion(Question question);
}
