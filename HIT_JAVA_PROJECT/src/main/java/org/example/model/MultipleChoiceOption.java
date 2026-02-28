package org.example.model;

public class MultipleChoiceOption {
    private int optionId;
    private int questionId;
    private String content;
    private boolean isCorrect;

    public MultipleChoiceOption() {}
    public MultipleChoiceOption(int optionId, int questionId, String content, boolean isCorrect) {
        this.optionId = optionId;
        this.questionId = questionId;
        this.content = content;
        this.isCorrect = isCorrect;
    }

    public int getOptionId() {
        return optionId;
    }

    public int getQuestionId() {
        return questionId;
    }
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
