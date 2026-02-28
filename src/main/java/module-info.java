module com.example.hellowordjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hellowordjavafx to javafx.fxml;
    exports com.example.hellowordjavafx;
}