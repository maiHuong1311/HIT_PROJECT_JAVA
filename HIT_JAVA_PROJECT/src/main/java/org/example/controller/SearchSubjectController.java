package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import org.example.constant.Common;
import org.example.constant.ErrorMessage;
import org.example.model.Subject;
import org.example.service.SearchSubjectService;
import org.example.utils.SceneUtil;

import java.io.IOException;
import java.util.List;

public class SearchSubjectController {
    @FXML private VBox searchErrorVBox;
    @FXML private VBox resultVBox;
    @FXML private Label lblSearchError;
    @FXML private ScrollPane searchScrollPane;

    @FXML public void initData(List<Subject> result) {
        resultVBox.getChildren().clear();
        if(result == null || result.isEmpty()) {
            searchErrorVBox.setVisible(true);
            searchErrorVBox.setManaged(true);
            lblSearchError.setText(ErrorMessage.SEARCH_FAILED);
            searchScrollPane.setVisible(false);
            searchScrollPane.setManaged(false);
        }
        else {
            searchErrorVBox.setVisible(false);
            searchErrorVBox.setManaged(false);
            searchScrollPane.setVisible(true);
            searchScrollPane.setManaged(true);
            try {
                for(Subject s : result) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/subject_item.fxml"));
                    Parent item = loader.load();
                    SubjectItemController itemController = loader.getController();
                    itemController.setData(s);
                    resultVBox.getChildren().add(item);
                }
            } catch(IOException e) {
                SceneUtil.showErrorAlert(Common.ERROR_TITLE, Common.ERROR_HEADER, e.getMessage());
            }
        }
    }

    @FXML public void handleSwitchToHome(ActionEvent event) {
        SceneUtil.changeScene(event, "/view/home.fxml", "Trang chủ");
    }
}
