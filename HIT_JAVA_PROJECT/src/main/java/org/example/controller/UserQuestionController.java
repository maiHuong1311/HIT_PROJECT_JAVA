package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.example.model.MultipleChoiceQuestion;
import org.example.model.UserQuestion;
import org.example.service.LoginService;
import org.example.service.QuestionService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserQuestionController {
    private QuestionService questionService = new QuestionService();
    @FXML private Label titleLabel;
    @FXML private VBox answerVBox;

    @FXML public void displayUserQuestion(UserQuestion uq) {
        titleLabel.setText(uq.getTitle());
        answerVBox.getChildren().clear();
        List<String> allOptions = new ArrayList<>();
        allOptions.add(uq.getOptionA());
        allOptions.add(uq.getOptionB());
        allOptions.add(uq.getOptionC());
        allOptions.add(uq.getCorrectAnswer());
        Collections.shuffle(allOptions);
        for (String answerContent : allOptions) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/answer_item.fxml"));
                Parent answerNode = loader.load();
                AnswerItemController itemController = loader.getController();
                itemController.setAnswer(answerContent, uq.getCorrectAnswer());
                itemController.setAnswerData(uq.getUserQuestionId(), answerContent, null);
                answerVBox.getChildren().add(answerNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
