package org.example.model;

public class Question {
    private String id;
    private String content;
    private int lessonId;

    public Question() {}
    public Question(String id, String content, int lessonId) {
        this.id = id;
        this.content = content;
        this.lessonId = lessonId;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {

        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public int getLessonId() {
        return lessonId;
    }
    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }
}
