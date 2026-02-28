package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.model.Exercise;
import org.example.model.Lesson;
import org.example.model.MultipleChoiceQuestion;
import org.example.service.LoginService;
import org.example.service.QuestionService;
import org.example.utils.SceneUtil;

import java.io.IOException;
import java.util.List;

public class ExerciseReviewController {
    private Lesson selectedLesson;
    private List<Exercise> exerciseList;
    private QuestionService questionService = new QuestionService();

    @FXML Button backButton;
    @FXML ScrollPane reviewScrollPane;
    @FXML VBox reviewVBox;

    public void exerciseReview(Lesson lesson) {
        this.selectedLesson = lesson;
        exerciseList = questionService.getAllExercise(lesson.getLessonId(), LoginService.currentUser.getId());
        reviewVBox.getChildren().clear();
        for(Exercise exercise : exerciseList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/exercise_item.fxml"));
                Parent exerciseNode = loader.load();
                ExerciseController exerciseController = loader.getController();
                exerciseController.displayExercise(exercise, lesson.getLessonId());
                reviewVBox.getChildren().add(exerciseNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML public void handleSwitchToQuestionType(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/chooseQuestionType.fxml"));
            Parent root = loader.load();
            QuestionTypeController controller = loader.getController();
            controller.setLesson(selectedLesson);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
