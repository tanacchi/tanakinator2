package com.example.tanakinator2.domain;

public class Choice {  // TODO: Make me Enum
    private int choiceId;
    private String choiceName;
    private float choiceValue;

    public Choice() {
    }

    public Choice(int choiceId, String choiceName, float choiceValue) {
        this.choiceId = choiceId;
        this.choiceName = choiceName;
        this.choiceValue = choiceValue;
    }

    public int getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(int choiceId) {
        this.choiceId = choiceId;
    }

    public String getChoiceName() {
        return choiceName;
    }

    public void setChoiceName(String choiceName) {
        this.choiceName = choiceName;
    }

    public float getChoiceValue() {
        return choiceValue;
    }

    public void setChoiceValue(float choiceValue) {
        this.choiceValue = choiceValue;
    }
}
