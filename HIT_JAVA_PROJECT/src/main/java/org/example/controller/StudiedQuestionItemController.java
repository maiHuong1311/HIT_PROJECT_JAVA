package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.model.dto.HistoryQuestion;

public class StudiedQuestionItemController {
    @FXML Label titleLabel;
    @FXML Label userAnswerLabel;
    @FXML Label correctAnswerLabel;

    public void setData(HistoryQuestion historyQuestion) {
        titleLabel.setText(historyQuestion.getTitle());
        userAnswerLabel.setText(historyQuestion.getUserAnswer());
        correctAnswerLabel.setText(historyQuestion.getCorrectAnswer());
    }
}
