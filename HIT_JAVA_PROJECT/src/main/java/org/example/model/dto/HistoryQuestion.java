package org.example.model.dto;

public class HistoryQuestion {
    private String title;
    private String userAnswer;
    private String correctAnswer;

    public HistoryQuestion() {}
    public HistoryQuestion(String title, String userAnswer, String correctAnswer) {
        this.title = title;
        this.userAnswer = userAnswer;
        this.correctAnswer = correctAnswer;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserAnswer() {
        return userAnswer;
    }
    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
