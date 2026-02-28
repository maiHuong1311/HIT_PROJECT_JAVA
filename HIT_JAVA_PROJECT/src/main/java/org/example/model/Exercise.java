package org.example.model;

public class Exercise extends Question {
    private String exampleAnswer;

    public Exercise() {}
    public Exercise(int questionId, int lessonId, String title, QuestionType questionType, boolean isComplete, String exampleAnswer) {
        super(questionId, lessonId, title, questionType, isComplete);
        this.exampleAnswer = exampleAnswer;
    }

    public String getExampleAnswer() {
        return exampleAnswer;
    }
    public void setExampleAnswer(String exampleAnswer) {
        this.exampleAnswer = exampleAnswer;
    }
}
