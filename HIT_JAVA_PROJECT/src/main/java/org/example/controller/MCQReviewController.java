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
import org.example.model.Lesson;
import org.example.model.MultipleChoiceQuestion;
import org.example.service.LoginService;
import org.example.service.QuestionService;

import java.io.IOException;
import java.util.List;

public class MCQReviewController {
    private Lesson selectedLesson;
    private int currentLessonId;
    private List<MultipleChoiceQuestion> mcqList;
    private QuestionService questionService = new QuestionService();
    @FXML Button backButton;
    @FXML ScrollPane reviewScrollPane;
    @FXML VBox reviewVBox;
    public void mcqReview(Lesson lesson) {
        this.selectedLesson = lesson;
        this.currentLessonId = lesson.getLessonId();
        int userId = LoginService.currentUser.getId();
        mcqList = questionService.getAllMultipleChoiceQuestion(currentLessonId, userId);
        for(MultipleChoiceQuestion mcq : mcqList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/question_item.fxml"));
                Parent mcqQuestionNode = loader.load();
                MCQController mcqController = loader.getController();
                mcqController.setLessonId(currentLessonId);
                mcqController.displayMCQQuestion(mcq);
                reviewVBox.getChildren().add(mcqQuestionNode);
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
