package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.example.constant.Common;
import org.example.constant.ErrorMessage;
import org.example.constant.SuccessfulMessage;
import org.example.model.Role;
import org.example.model.UserQuestion;
import org.example.service.LoginService;
import org.example.service.QuestionService;
import org.example.utils.SceneUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddQuestionController {
    private int count;
    private List<AddQuestionItemController> controllerList = new ArrayList<>();
    private QuestionService questionService = new QuestionService();
    private String subjectName;
    private String totalQuestion;
    @FXML Label lblTotalQuestionError1;
    @FXML Label lblNameError;
    @FXML Label lblTotalQuestionError;
    @FXML TextField subjectNameTextField;
    @FXML TextField totalQuestionTextField;
    @FXML Button addButton;
    @FXML ScrollPane addScrollPane;
    @FXML VBox addListVBox;
    @FXML Button saveButton;

    @FXML public void initialize() {
        subjectNameTextField.setOnMouseClicked(event -> {
            lblNameError.setText("");
        });
        totalQuestionTextField.setOnMouseClicked(event -> {
            lblTotalQuestionError.setText("");
        });
    }

    @FXML public void saveUserQuestion(ActionEvent event) {
        this.subjectName = subjectNameTextField.getText().trim();
        this.totalQuestion = totalQuestionTextField.getText().trim();
        controllerList.clear();
        addListVBox.getChildren().clear();
        if(validateInput(subjectName, totalQuestion)) {
            addScrollPane.setVisible(true);
            saveButton.setVisible(true);
            for(int i = 1; i <= count; i++) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/add_question_item.fxml"));
                    Parent root = loader.load();
                    AddQuestionItemController controller = loader.getController();
                    controllerList.add(controller);
                    addListVBox.getChildren().add(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            subjectNameTextField.setDisable(true);
            totalQuestionTextField.setDisable(true);
            addButton.setDisable(true);
        }
    }

    @FXML
    public void handleSubmit(ActionEvent event) {
        boolean check = false;
        for (AddQuestionItemController controller : controllerList) {
            UserQuestion uq = controller.getDataFromUser(LoginService.currentUser.getId(), count, this.subjectName);
            check = questionService.insertUserQuestion(uq);
        }
        if(check) {
            SceneUtil.showSuccessfulMessage(Common.SUCCESS_TITLE, Common.SUCCESS_HEADER, SuccessfulMessage.SAVE_SUCCESSFULLY);
            SceneUtil.changeScene(event, "/view/home.fxml", "Trang chủ");
        }
    }

    private boolean validateInput(String subjectName, String totalQuestion) {
        lblNameError.setText("");
        lblTotalQuestionError.setText("");
        lblTotalQuestionError1.setText("");
        boolean hasError = false;
        if(subjectName.isEmpty()) {
            lblNameError.setText(Common.NOT_NULL_SUBJECT_NAME_TEXT_FIELD);
            hasError = true;
        }
        if (totalQuestion.isEmpty()) {
            lblTotalQuestionError.setText(Common.NOT_NULL_TOTAL_QUESTION);
            hasError = true;
        } else {
            try {
                this.count = Integer.parseInt(totalQuestion);
                if(this.count <= 0) {
                    lblTotalQuestionError1.setText(ErrorMessage.INVALID_TOTAL_QUESTION);
                    hasError = true;
                }
            } catch (NumberFormatException e) {
                lblTotalQuestionError.setText(ErrorMessage.INVALID_NUMBER_FORMART);
                hasError = true;
            }
        }
        return !hasError;
    }
}
