package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.model.Subject;

public class SubjectItemController {
    @FXML Label lblSubjectName;
    @FXML Label lblQuestion;
    @FXML Label lblExercise;

    public void setData(Subject subject) {
        lblSubjectName.setText(subject.getName());
        lblQuestion.setText(String.valueOf(subject.getTotalQuestion()));
        lblExercise.setText(String.valueOf(subject.getTotalExercise()));
    }
}
