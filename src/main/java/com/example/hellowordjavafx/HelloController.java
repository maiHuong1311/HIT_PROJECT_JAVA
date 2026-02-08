package com.example.hellowordjavafx;

        import javafx.fxml.FXML;
        import javafx.scene.input.MouseEvent;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.stage.Stage;
        import java.io.IOException;
        import javafx.animation.Interpolator;
        import javafx.animation.KeyFrame;
        import javafx.animation.KeyValue;
        import javafx.animation.Timeline;
        import javafx.scene.control.ScrollPane;
        import javafx.util.Duration;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;
        import javafx.scene.layout.FlowPane;
        import javafx.scene.layout.Pane;

public class HelloController {
    @FXML
    private ScrollPane myScrollPane;

    private void changeScene(MouseEvent event, String fxmlFile) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void handleHistoryClick(MouseEvent event) throws IOException {
        changeScene(event, "History.fxml");
    }

    @FXML
    public void handleCoursesClick(MouseEvent event) throws IOException {
        changeScene(event, "mysourses.fxml");
    }

    @FXML
    public void handleAddClick(MouseEvent event) throws IOException {
        changeScene(event, "Addsubject.fxml");
    }
    @FXML
    public void handleNotificationsClick(MouseEvent event) throws IOException {
        changeScene(event, "Notification.fxml");
    }

    @FXML
    public void handleHomeClick(MouseEvent event) throws IOException {
        changeScene(event, "hello-view.fxml");
    }
    @FXML
    private TextField searchField;
    @FXML
    private TextField searchField_Parts;
    @FXML
    private FlowPane subjectsContainer;
    @FXML
    public void initialize() {
        if (myScrollPane != null) {
            myScrollPane.setFitToWidth(true);
            myScrollPane.setOnScroll(ev -> {
                double deltaY = ev.getDeltaY() * 20.0;
                double contentHeight = myScrollPane.getContent().getBoundsInLocal().getHeight();
                double targetVvalue = myScrollPane.getVvalue() - deltaY / contentHeight;
                targetVvalue = Math.max(0, Math.min(1, targetVvalue));
                Timeline timeline = new Timeline();
                KeyValue keyValue = new KeyValue(myScrollPane.vvalueProperty(), targetVvalue, Interpolator.EASE_BOTH);
                KeyFrame keyFrame = new KeyFrame(Duration.millis(1200), keyValue);
                timeline.getKeyFrames().add(keyFrame);
                timeline.play();
                ev.consume();
            });
        }

        if (searchField != null) {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filterSubjects(newValue);
            });
        }
        if (searchField != null) {
            searchField.setFocusTraversable(false);
        }
        if (searchField_Parts != null) {
            searchField_Parts.textProperty().addListener((observable, oldValue, newValue) -> {
                filterSubjects(newValue);
            });
        }
        if (searchField_Parts != null) {
            searchField_Parts.setFocusTraversable(false);
        }
    }
    private void filterSubjects(String searchText) {
        String filter = searchText.toLowerCase().trim();

        for (Node node : subjectsContainer.getChildren()) {
            if (node instanceof Pane) {
                Pane box = (Pane) node;
                String subjectName = "";
                for (Node child : box.getChildren()) {
                    if (child instanceof Label) {
                        subjectName = ((Label) child).getText().toLowerCase();
                        break;
                    }
                }

                if (subjectName.contains(filter)) {
                    box.setVisible(true);
                    box.setManaged(true);
                } else {
                    box.setVisible(false);
                    box.setManaged(false);
                }
            }
        }
        myScrollPane.setVvalue(0);
    }
}

