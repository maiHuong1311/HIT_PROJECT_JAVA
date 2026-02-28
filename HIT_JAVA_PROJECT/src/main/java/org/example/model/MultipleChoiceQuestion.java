package org.example.model;

import java.util.List;

public class MultipleChoiceQuestion extends Question {
    private List<String> answer;
    private int rightAnswer;

    public MultipleChoiceQuestion() {}
    public MultipleChoiceQuestion(int questionId, int lessonId, String title, QuestionType questionType, boolean isComplete, List<String> answer, int rightAnswer) {
        super(questionId, lessonId, title, questionType, isComplete);
        this.answer = answer;
        this.rightAnswer = rightAnswer;
    }

    public List<String> getAnswer() {
        return answer;
    }
    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }
    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}
