package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.constant.Common;
import org.example.model.Subject;
import org.example.utils.SceneUtil;

import java.io.IOException;
import java.util.List;

public class SubjectItemController {
    private boolean isHistoryMode = false;
    private Subject subjectCurrent;
    @FXML Label lblSubjectName;
    @FXML Label lblQuestion;
    @FXML Label lblExercise;

    @FXML public void setData(Subject subject) {
        this.subjectCurrent = subject;
        lblSubjectName.setText(subject.getName());
        lblQuestion.setText(String.valueOf(subject.getTotalQuestion()));
        lblExercise.setText(String.valueOf(subject.getTotalExercise()));
    }

    public void setHistoryMode(boolean isHistoryMode) {
        this.isHistoryMode = isHistoryMode;
    }

    public void show(ActionEvent event) {
        int subjectId = subjectCurrent.getSubjectId();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/lesson.fxml"));
            Parent root = loader.load();
            ShowLessonController showLessonController = loader.getController();
            showLessonController.loadData(subjectId);
            showLessonController.setHistoryMode(this.isHistoryMode);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Bài");
            stage.show();
        } catch (IOException e) {
            SceneUtil.showErrorAlert(Common.ERROR_TITLE, Common.ERROR_HEADER, e.getMessage());
        }
    }
}
