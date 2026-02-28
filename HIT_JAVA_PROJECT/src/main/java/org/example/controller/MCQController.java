package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.example.dao.QuestionDAO;
import org.example.model.MultipleChoiceQuestion;
import org.example.service.LoginService;
import org.example.service.QuestionService;

import java.io.IOException;

public class MCQController {
    private QuestionService questionService = new QuestionService();
    @FXML private Label titleLabel;
    @FXML private VBox answerVBox;
    private int lessonId;

    public int getLessonId() {
        return this.lessonId;
    }
    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    @FXML public void displayMCQQuestion(MultipleChoiceQuestion question) {
        titleLabel.setText(question.getTitle());
        answerVBox.getChildren().clear();
        for (String answerContent : question.getAnswer()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/answer_item.fxml"));
                Parent answerNode = loader.load();
                AnswerItemController itemController = loader.getController();
                itemController.setAnswerData(question.getQuestionId(), answerContent, this);
                if (question.isComplete()) {
                    answerNode.setMouseTransparent(true);
                    String correctAnswer = questionService.getCorrectAnswer(question.getQuestionId());
                    if (answerContent.equals(correctAnswer)) {
                        itemController.setStyleCorrect();
                    } else
                        itemController.setStyleIncorrect();
                }
                answerNode.getProperties().put("controller", itemController);
                answerVBox.getChildren().add(answerNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML public void showCorrectAnswer(int questionId) {
        String correctAnswerContent = questionService.getCorrectAnswer(questionId);
        for (javafx.scene.Node node : answerVBox.getChildren()) {
            AnswerItemController itemController = (AnswerItemController) node.getProperties().get("controller");
            if (itemController != null) {
                String currentContent = itemController.getAnswerContent().trim();
                String targetContent = correctAnswerContent.trim();
                if (currentContent.equalsIgnoreCase(targetContent)) {
                    itemController.setStyleCorrect();
                    break;
                }
            }
        }
    }

    @FXML public void disableAllAnswers() {
        for (javafx.scene.Node node : answerVBox.getChildren()) {
            node.setMouseTransparent(true);
            node.setOpacity(1.0);
            node.setFocusTraversable(false);
        }
    }
}
