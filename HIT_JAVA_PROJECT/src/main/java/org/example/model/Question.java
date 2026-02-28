package org.example.model;

public abstract class Question {
    private int questionId;
    private int lessonId;
    private String title;
    private QuestionType questionType;
    private boolean isComplete;

    public Question() {}
    public Question(int questionId, int lessonId, String title, QuestionType questionType, boolean isComplete) {
        this.questionId = questionId;
        this.lessonId = lessonId;
        this.title = title;
        this.questionType = questionType;
        this.isComplete = isComplete;
    }

    public int getQuestionId() {
        return questionId;
    }
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getLessonId() {
        return lessonId;
    }
    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }
    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public boolean isComplete() {
        return isComplete;
    }
    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
