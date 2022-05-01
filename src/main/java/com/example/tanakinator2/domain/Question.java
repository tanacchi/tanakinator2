package com.example.tanakinator2.domain;

public class Question {
    private int questionId;
    private String message;

    public Question() {
    }

    public Question(int questionId, String message) {
        this.questionId = questionId;
        this.message = message;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
