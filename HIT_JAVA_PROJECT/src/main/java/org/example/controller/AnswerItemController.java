package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.constant.Common;
import org.example.dao.QuestionDAO;
import org.example.model.MultipleChoiceQuestion;
import org.example.model.Question;
import org.example.service.LoginService;
import org.example.service.QuestionService;

import java.util.ArrayList;
import java.util.List;

public class AnswerItemController {
    private MCQController parentController;
    private QuestionService questionService = new QuestionService();
    private int currentQuestionId;
    private String answerContent;
    @FXML Button answerButton;

    public void setAnswerData(int questionId, String content, MCQController parent) {
        this.currentQuestionId = questionId;
        this.answerContent = content;
        this.parentController = parent;
        answerButton.setText(content);
    }

    public String getAnswerContent() {
        return this.answerContent;
    }

    public void setStyleCorrect() {
        answerButton.setStyle(Common.SET_STYLE_CORRECT_ANSWER);
    }

    public void setStyleIncorrect() {
        answerButton.setStyle(Common.SET_STYLE_INCORRECT_ANSWER);
    }

    @FXML public void handleAnswerClick(ActionEvent event) {
        boolean isCorrect = questionService.checkAnswer(currentQuestionId, answerContent);
        int userId = LoginService.currentUser.getId();
        int lessonId = parentController.getLessonId();
        String correctAnswer = questionService.getCorrectAnswer(currentQuestionId);
        questionService.saveUserProgress(userId, currentQuestionId, lessonId, 1,isCorrect ? 1 : 0, answerContent, correctAnswer);
        if (isCorrect) {
            answerButton.setStyle(Common.SET_STYLE_CORRECT_ANSWER);
        } else {
            answerButton.setStyle(Common.SET_STYLE_INCORRECT_ANSWER);
            parentController.showCorrectAnswer(currentQuestionId);
        }
        parentController.disableAllAnswers();
    }
}
