package org.example.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.constant.Common;
import org.example.constant.ErrorMessage;
import org.example.constant.SuccessfulMessage;
import org.example.model.Role;
import org.example.service.LoginService;
import org.example.utils.EmailUtil;
import org.example.utils.PasswordUtil;
import org.example.utils.SceneUtil;

public class LoginController {
    @FXML private Button loginButton;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private ComboBox<Role> roleComboBox;
    @FXML private Label lblErrorRole;
    @FXML private Label lblErrorUsername;
    @FXML private Label lblErrorPassword;
    @FXML private Label lblErrorMessage;
    @FXML private Hyperlink registerHyperlink;
    @FXML private Hyperlink forgotPasswordHyperlink;
    private LoginService loginService = new LoginService();

    @FXML public void initialize() {
        roleComboBox.setItems(FXCollections.observableArrayList(Role.values()));
    }

    @FXML public void handleLogin(ActionEvent event) {
        clearAllErrorLabels();
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        Role selectedRole = roleComboBox.getValue();
        if (validateInput(username, password, selectedRole)) {
            try {
                loginService.loginService(username, password, selectedRole);

            } catch (Exception e) {
                lblErrorMessage.setText(e.getMessage());
            }
        }
    }

    @FXML public void handleSwitchToRegister(ActionEvent event) {
        SceneUtil.changeScene(event, "/view/register.fxml", "Đăng ký tài khoản");
    }

    @FXML public void handleForgotPassword(ActionEvent event) {
        SceneUtil.changeScene(event, "/view/forgetPassword.fxml", "Khôi phục mật khẩu");
    }

    private boolean validateInput(String username, String password, Role role) {
        boolean hasError = false;
        if(role == null) {
            lblErrorRole.setText(Common.NOT_NULL_ROLE);
            hasError = true;
        }
        if (username.isEmpty()) {
            lblErrorUsername.setText(Common.NOT_NULL_USERNAME);
            hasError = true;
        }
        if (password.isEmpty()) {
            lblErrorPassword.setText(Common.NOT_NULL_PASSWORD);
            hasError = true;
        }
        return !hasError;
    }

    private void clearAllErrorLabels() {
        lblErrorUsername.setText("");
        lblErrorPassword.setText("");
        lblErrorRole.setText("");
        lblErrorMessage.setText("");
    }
}