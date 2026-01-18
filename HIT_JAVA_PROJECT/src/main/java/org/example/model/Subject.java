package org.example.model;

public class Subject {
    private String id;
    private String name;
    private int totalQuestions;
    private int totalExercises;

    public Subject() {}
    public Subject(String id, String name, int totalQuestions, int totalExercises) {
        this.id = id;
        this.name = name;
        this.totalQuestions = totalQuestions;
        this.totalExercises = totalExercises;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }
    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getTotalExercises() {
        return totalExercises;
    }
    public void setTotalExercises(int totalExercises) {
        this.totalExercises = totalExercises;
    }
}
