package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.constant.Common;
import org.example.model.Lesson;
import org.example.model.UserQuestion;
import org.example.service.LoginService;
import org.example.service.QuestionService;
import org.example.utils.SceneUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddQuestionHistoryController {
    private QuestionService questionService = new QuestionService();
    @FXML Hyperlink addQuestionHyperlink;
    @FXML Button backButton;
    @FXML ScrollPane scrollPane;
    @FXML VBox questionListVBox;

    @FXML public void showUserQuestion() {
        List<UserQuestion> list = questionService.getUserQuestionsByUserId(LoginService.currentUser.getId());
        questionListVBox.getChildren().clear();
        List<Hyperlink> links = new ArrayList<>();
        for(UserQuestion u : list) {
            Hyperlink hyperLink = new Hyperlink(u.getSubjectName());
            hyperLink.setStyle(Common.SET_STYLE_HYPERLINK);
            hyperLink.setOnAction(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user_question_review.fxml"));
                    Parent root = loader.load();
                    UserQuestionReviewController controller = loader.getController();
                    controller.userQuestionReview();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            links.add(hyperLink);
            questionListVBox.getChildren().add(hyperLink);
        }
    }

    @FXML public void handleSwitchToAddQuestion(ActionEvent event) {
        SceneUtil.changeScene(event, "/view/add_question.fxml", "Đăng ký tài khoản");
    }

    @FXML public void handleSwitchToHome(ActionEvent event) {
        SceneUtil.changeScene(event, "/view/home.fxml", "Trang chủ");
    }
}
