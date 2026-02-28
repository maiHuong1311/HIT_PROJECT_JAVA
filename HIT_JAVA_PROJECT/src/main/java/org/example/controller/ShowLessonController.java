package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.constant.Common;
import org.example.model.Lesson;
import org.example.service.LoginService;
import org.example.service.ShowLessonService;
import org.example.utils.SceneUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowLessonController {
    private boolean isHistoryMode = false;
    private ShowLessonService showLessonService = new ShowLessonService();
    @FXML private ScrollPane lessonScrollPane;
    @FXML private VBox lessonVBox;

    public void setHistoryMode(boolean isHistoryMode) {
        this.isHistoryMode = isHistoryMode;
    }

    @FXML public void loadData(int subjectId) {
        List<Lesson> result = showLessonService.showLesson(subjectId);
        lessonVBox.getChildren().clear();
        List<Hyperlink> links = new ArrayList<>();
        for(Lesson l : result) {
            Hyperlink hyperLink = new Hyperlink(l.getLessonId() + ". " + l.getTitle());
            hyperLink.setStyle(Common.SET_STYLE_HYPERLINK);
            hyperLink.setOnAction(event -> {
                if(isHistoryMode) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/studied_list.fxml"));
                        Parent root = loader.load();
                        StudiedQuestionListController controller = loader.getController();
                        controller.showStudiedList(LoginService.currentUser.getId(), l.getLessonId());
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/chooseQuestionType.fxml"));
                        Parent root = loader.load();
                        QuestionTypeController controller = loader.getController();
                        controller.setLesson(l);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            links.add(hyperLink);
        }
        lessonVBox.getChildren().addAll(links);
    }

    @FXML public void handleSwitchToHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/home.fxml"));
            Parent root = loader.load();
            HomeController controller = loader.getController();
            controller.initialize();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
