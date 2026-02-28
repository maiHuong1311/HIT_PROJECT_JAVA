package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import org.example.model.Lesson;
import org.example.model.MultipleChoiceQuestion;
import org.example.model.UserQuestion;
import org.example.service.LoginService;
import org.example.service.QuestionService;
import org.example.utils.SceneUtil;

import java.io.IOException;
import java.util.List;

public class UserQuestionReviewController {
    private QuestionService questionService = new QuestionService();
    @FXML Button backButton;
    @FXML ScrollPane reviewScrollPane;
    @FXML VBox reviewVBox;

    public void initialize() {
        userQuestionReview();
    }

    public void userQuestionReview() {
        int userId = LoginService.currentUser.getId();
        List<UserQuestion> userQuestions = questionService.getUserQuestionsByUserId(userId);
        reviewVBox.getChildren().clear();
        for (org.example.model.UserQuestion uq : userQuestions) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user_question.fxml"));
                Parent questionNode = loader.load();
                UserQuestionController controller = loader.getController();
                controller.displayUserQuestion(uq);
                reviewVBox.getChildren().add(questionNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML public void handleSwitchToHome(ActionEvent event) {
        SceneUtil.changeScene(event, "/view/home.fxml", "Trang chủ");
    }
}

