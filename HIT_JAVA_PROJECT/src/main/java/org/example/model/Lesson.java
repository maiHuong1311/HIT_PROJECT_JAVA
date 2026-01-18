package org.example.model;

public class Lesson {
    private int id;
    private String title;
    private int questionNumber;
    private int exerciseNumber;
    private String subjectId;

    public Lesson() {}
    public Lesson(int id, String title, int questionNumber, int exerciseNumber, String subjectId) {
        this.id = id;
        this.title = title;
        this.questionNumber = questionNumber;
        this.exerciseNumber = exerciseNumber;
        this.subjectId = subjectId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public int getExerciseNumber() {
        return exerciseNumber;
    }
    public void setExerciseNumber(int exerciseNumber) {
        this.exerciseNumber = exerciseNumber;
    }

    public String getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
}
