package org.example.model;

public class Lesson {
    private int lessonId;
    private int subjectId;
    private String title;

    public Lesson() {}
    public Lesson(int lessonId, int subjectId, String title) {
        this.lessonId = lessonId;
        this.subjectId = subjectId;
        this.title = title;
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
}
