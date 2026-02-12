package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.constant.Common;
import javafx.scene.Node;
import org.example.model.Subject;
import org.example.service.RandomSubjectDisplayService;
import org.example.service.SearchSubjectService;
import org.example.utils.SceneUtil;

import java.io.IOException;
import java.util.List;

public class HomeController {
    @FXML private TextField inputBarTextField;
    @FXML private Button searchButton;
    @FXML private Label lblNameError;
    @FXML private ScrollPane scrollPane;
    @FXML private VBox randomSubjectVBox;
    private SearchSubjectService searchSubjectService = new SearchSubjectService();
    private RandomSubjectDisplayService random = new RandomSubjectDisplayService();

    @FXML public void initialize() {
        inputBarTextField.setOnMouseClicked(event -> {
            lblNameError.setText("");
        });
        randomSubject();
    }

    @FXML public void searchSubject(ActionEvent event) {
        String subjectName = inputBarTextField.getText().trim();
        if(validateInput(subjectName)) {
            try {
                List<Subject> result = searchSubjectService.searchSubject(subjectName);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/search.fxml"));
                Parent root = loader.load();
                SearchSubjectController searchSubjectController = loader.getController();
                searchSubjectController.initData(result);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Kết quả tìm kiếm");
                stage.show();
            } catch (IOException e) {
                SceneUtil.showErrorAlert(Common.ERROR_TITLE, Common.ERROR_HEADER, e.getMessage());
            }
        }
    }

    @FXML public void randomSubject() {
        try {
            List<Subject> randomList = random.randomSubject();
            randomSubjectVBox.getChildren().clear();
            for(Subject s : randomList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/subject_item.fxml"));
                Parent item = loader.load();
                SubjectItemController itemController = loader.getController();
                itemController.setData(s);
                randomSubjectVBox.getChildren().add(item);
            }
        }  catch (IOException e) {
            SceneUtil.showErrorAlert(Common.ERROR_TITLE, Common.ERROR_HEADER, e.getMessage());
        }
    }
    private boolean validateInput(String name) {
        boolean hasError = false;
        if(name.isEmpty()) {
            lblNameError.setText(Common.NOT_NULL_SUBJECT_NAME);
            hasError = true;
        }
        return !hasError;
    }

}
