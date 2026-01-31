package org.example.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.constant.Common;
import org.example.constant.ErrorMessage;
import org.example.constant.SuccessfulMessage;
import org.example.exception.AuthenticationException;
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
    @FXML private TextField passwordTextField;
    @FXML private ImageView showPasswordImg;
    @FXML private ImageView hidePasswordImg;
    private LoginService loginService = new LoginService();

    @FXML public void initialize() {
        roleComboBox.setItems(FXCollections.observableArrayList(Role.values()));
        passwordTextField.setVisible(false);
        showPasswordImg.setVisible(false);
    }

    @FXML public void handleLogin(ActionEvent event) {
        clearAllErrorLabels();
        String username = usernameTextField.getText().trim();
        String password = passwordField.getText().trim();
        Role selectedRole = roleComboBox.getValue();
        if (validateInput(username, password, selectedRole)) {
            try {
                loginService.loginService(username, password, selectedRole);

            } catch (AuthenticationException e) {
                lblErrorMessage.setText(e.getMessage());
            }
        }
    }

    @FXML public void showPassword(ActionEvent event) {
        if(!passwordTextField.isVisible()) {
            passwordTextField.setText(passwordField.getText());
            passwordTextField.setVisible(true);
            showPasswordImg.setVisible(true);
            passwordField.setVisible(false);
            hidePasswordImg.setVisible(false);
        }
        else {
            passwordTextField.setVisible(false);
            showPasswordImg.setVisible(false);
            passwordField.setVisible(true);
            hidePasswordImg.setVisible(true);
        }
    }

    @FXML public void handleSwitchToRegister(ActionEvent event) {
        SceneUtil.changeScene(event, "/view/register.fxml", "Đăng ký tài khoản");
    }

    @FXML public void handleSwitchToForgotPassword(ActionEvent event) {
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