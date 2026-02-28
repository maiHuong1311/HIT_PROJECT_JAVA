package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import org.example.constant.Common;
import org.example.dao.QuestionDAO;
import org.example.model.Exercise;
import org.example.service.LoginService;
import org.example.service.QuestionService;

public class ExerciseController {
    private QuestionService questionService = new QuestionService();
    private int currentQuestionId;
    private int currentLessonId;
    @FXML Label titleLabel;
    @FXML Button showExampleAnswerButton;
    @FXML Label exampleAnswerLabel;
    @FXML ScrollPane exerciseScrollPane;
    @FXML VBox exampleAnswerVBox;

    public void displayExercise(Exercise exercise, int lessonId) {
        this.currentLessonId = lessonId;
        this.currentQuestionId = exercise.getQuestionId();
        titleLabel.setText(exercise.getTitle());
        exampleAnswerLabel.setText(exercise.getExampleAnswer());
        exerciseScrollPane.setVisible(false);
    }

    public void showExampleAnswer(ActionEvent event) {
        exerciseScrollPane.setVisible(true);
        int userId = LoginService.currentUser.getId();
        questionService.saveUserProgress(userId, currentQuestionId, currentLessonId, 1, 1, Common.USER_ANSWER ,exampleAnswerLabel.getText());
        showExampleAnswerButton.setDisable(true);
    }
}
