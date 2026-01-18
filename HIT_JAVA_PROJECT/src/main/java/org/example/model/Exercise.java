package org.example.model;

public class Exercise extends Question {
    private String sampleAnswer;

    public Exercise() {}
    public Exercise(String id, String content, int lessonId, String sampleAnswer) {
        super(id, content, lessonId);
        this.sampleAnswer = sampleAnswer;
    }

    public String getSampleAnswer() {
        return sampleAnswer;
    }
    public void setSampleAnswer(String sampleAnswer) {
        this.sampleAnswer = sampleAnswer;
    }
}
