package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.constant.Common;
import org.example.model.Lesson;
import org.example.service.LoginService;
import org.example.service.QuestionService;
import javafx.scene.control.Label;
import org.example.utils.SceneUtil;

import java.io.IOException;

public class QuestionTypeController {
    private QuestionService questionService = new QuestionService();
    private Lesson selectedLesson;
    @FXML Label mcqStatusLabel;
    @FXML Label exerciseStatusLabel;
    @FXML Button mcqReviewButton;
    @FXML Button exerciseReviewButton;
    @FXML Button backkButton;

    public void setLesson(Lesson lesson) {
        this.selectedLesson = lesson;
        if (lesson.getTotalQuestion() == 0) {
            mcqStatusLabel.setText(Common.NOT_HAVE_MCQ);
        } else {
            int completedMCQ = questionService.getCompletedMultipleChoiceQuestion(LoginService.currentUser.getId(), lesson.getLessonId());
            if (completedMCQ >= lesson.getTotalQuestion()) {
                mcqStatusLabel.setText(Common.COMPLETE_QUESTION);
            } else {
                mcqStatusLabel.setText(Common.NOT_COMPLETE_QUESTION);
            }
        }
        if (lesson.getTotalExercise() == 0) {
            exerciseStatusLabel.setText(Common.NOT_HAVE_EXERCISE);
        } else {
            int completedExercise = questionService.getCompletedExercise(LoginService.currentUser.getId(), lesson.getLessonId());
            if (completedExercise >= lesson.getTotalExercise()) {
                exerciseStatusLabel.setText(Common.COMPLETE_QUESTION);
            } else {
                exerciseStatusLabel.setText(Common.NOT_COMPLETE_QUESTION);
            }
        }
    }

    @FXML public void handleSwitchToMCQReview(ActionEvent event) {
        if(questionService.hasProgress(LoginService.currentUser.getId(), selectedLesson.getLessonId())) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
            alert.setTitle(Common.CONFIRM_SET_TITLE);
            alert.setHeaderText(Common.CONFIRM_SET_HEADER);
            alert.setContentText(Common.CONFIRM_SET_CONTENT);
            javafx.scene.control.ButtonType Continue = new javafx.scene.control.ButtonType(Common.CONTINUE_BUTTON);
            javafx.scene.control.ButtonType Reset = new javafx.scene.control.ButtonType(Common.RESET_BUTTON);
            javafx.scene.control.ButtonType Cancel = new javafx.scene.control.ButtonType(Common.CANCEL_BUTTON, javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(Continue, Reset, Cancel);
            java.util.Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            if (result.isPresent()) {
                if (result.get() == Continue) {
                    switchToMCQScreen(event);
                } else if (result.get() == Reset) {
                    questionService.resetLessonProgress(LoginService.currentUser.getId(), selectedLesson.getLessonId());
                    switchToMCQScreen(event);
                }
            }
        } else {
            switchToMCQScreen(event);
        }
    }

    @FXML public void handleSwitchToExerciseReview(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/exerciseReview.fxml"));
            Parent root = loader.load();
            ExerciseReviewController exerciseReviewController = loader.getController();
            exerciseReviewController.exerciseReview(this.selectedLesson);
            javafx.stage.Stage stage = (javafx.stage.Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.sizeToScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void switchToMCQScreen(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mcqReview.fxml"));
            Parent root = loader.load();
            MCQReviewController mcqReviewController = loader.getController();
            mcqReviewController.mcqReview(this.selectedLesson);
            javafx.stage.Stage stage = (javafx.stage.Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.sizeToScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void handleSwitchToShowLesson(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/lesson.fxml"));
            Parent root = loader.load();
            ShowLessonController showLessonController = loader.getController();
            showLessonController.loadData(this.selectedLesson.getSubjectId());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Bài");
            stage.show();
        } catch (IOException e) {
            SceneUtil.showErrorAlert(Common.ERROR_TITLE, Common.ERROR_HEADER, e.getMessage());
        }
    }
}
