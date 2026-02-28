package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import org.example.constant.Common;
import org.example.model.dto.HistoryQuestion;
import org.example.service.QuestionService;
import org.example.utils.SceneUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudiedQuestionListController {
    private QuestionService questionService = new QuestionService();
    @FXML ScrollPane studiedListScrollPane;
    @FXML VBox studiedListVBox;
    @FXML Label lblNullQuestion;
    @FXML Button backButton;

    @FXML public void showStudiedList(int userId, int lessonId) {
        List<HistoryQuestion> historyList = questionService.getStudiedQuestionList(userId, lessonId);
        studiedListVBox.getChildren().clear();
        if(historyList.isEmpty()) {
            lblNullQuestion.setText(Common.NULL_QUESTION);
        } else {
            lblNullQuestion.setVisible(false);
            for(HistoryQuestion h : historyList) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/studied_question.fxml"));
                    Parent root = loader.load();
                    StudiedQuestionItemController sqic = loader.getController();
                    sqic.setData(h);
                    studiedListVBox.getChildren().add(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML public void handleSwitchToHome(ActionEvent event) {
        SceneUtil.changeScene(event, "/view/home.fxml", "Trang chủ");
    }
}
