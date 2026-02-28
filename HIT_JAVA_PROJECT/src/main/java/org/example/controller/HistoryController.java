package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import org.example.constant.Common;
import org.example.model.Subject;
import org.example.service.LoginService;
import org.example.service.QuestionService;
import org.example.utils.SceneUtil;

import java.io.IOException;
import java.util.List;

public class HistoryController {
    private QuestionService questionService = new QuestionService();
    @FXML Label lblHistory;
    @FXML ScrollPane historyScrollPane;
    @FXML VBox historyVBox;

    @FXML public void loadStudiedSubjects(int userId) {
        List<Subject> studiedList = questionService.getStudiedSubject(userId);
        historyVBox.getChildren().clear();
        if(studiedList.isEmpty()) {
            lblHistory.setText(Common.NULL_HISTORY);
        } else {
            lblHistory.setVisible(false);
            try {
                for(Subject s : studiedList) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/subject_item.fxml"));
                    Parent item = loader.load();
                    SubjectItemController itemController = loader.getController();
                    itemController.setData(s);
                    itemController.setHistoryMode(true);
                    historyVBox.getChildren().add(item);
                }
            } catch(IOException e) {
                SceneUtil.showErrorAlert(Common.ERROR_TITLE, Common.ERROR_HEADER, e.getMessage());
            }
        }
    }
}
