package org.example.model;

public class Lesson {
    private int lessonId;
    private int subjectId;
    private String title;
    private int totalQuestion;
    private int totalExercise;

    public Lesson() {}
    public Lesson(int lessonId, int subjectId, String title, int totalQuestion, int totalExercise) {
        this.lessonId = lessonId;
        this.subjectId = subjectId;
        this.title = title;
        this.totalQuestion = totalQuestion;
        this.totalExercise = totalExercise;
    }

    public int getLessonId() {
        return lessonId;
    }

    public int getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
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
