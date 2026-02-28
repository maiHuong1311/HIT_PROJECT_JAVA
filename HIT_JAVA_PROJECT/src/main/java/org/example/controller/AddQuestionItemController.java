package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.model.UserQuestion;

public class AddQuestionItemController {
    @FXML TextField titleTextField;
    @FXML TextField optionATextField;
    @FXML TextField optionBTextField;
    @FXML TextField optionCTextField;
    @FXML TextField correctAnswerTextField;

    public UserQuestion getDataFromUser(int userId, int questionNumber, String subjectName) {
        String title = titleTextField.getText().trim();
        String optionA = optionATextField.getText().trim();
        String optionB = optionBTextField.getText().trim();
        String optionC = optionCTextField.getText().trim();
        String correctAnswer = correctAnswerTextField.getText();
        return new UserQuestion(userId, questionNumber, subjectName, title, optionA, optionB, optionC, correctAnswer);
    }
}
