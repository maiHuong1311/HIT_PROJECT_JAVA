package org.example.model;

public class UserQuestion {
    private int userQuestionId;
    private int id;
    private int questionNumber;
    private String subjectName;
    private String title;
    private String optionA;
    private String optionB;
    private String optionC;
    private String correctAnswer;

    public UserQuestion() {}
    public UserQuestion(int id, int questionNumber, String subjectName, String title, String optionA, String optionB, String optionC, String correctAnswer) {
        this.id = id;
        this.questionNumber = questionNumber;
        this.subjectName = subjectName;
        this.title = title;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.correctAnswer = correctAnswer;
    }

    public int getUserQuestionId() {
        return userQuestionId;
    }
    public void setUserQuestionId(int userQuestionId) {
        this.userQuestionId = userQuestionId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getOptionA() {
        return optionA;
    }
    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }
    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }
    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
