package org.example.model;

public class Subject {
    private int subjectId;
    private String name;
    private int totalQuestion;
    private int totalExercise;

    public Subject() {}
    public Subject(int subjectId, String name, int totalQuestion, int totalExercise) {
        this.subjectId = subjectId;
        this.name = name;
        this.totalQuestion = totalQuestion;
        this.totalExercise = totalExercise;
    }

    public int getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getTotalQuestion() {
        return totalQuestion;
    }
    public void setTotalQuestion(int totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    public int getTotalExercise() {
        return totalExercise;
    }
    public void setTotalExercise(int totalExercise) {
        this.totalExercise = totalExercise;
    }
}
