package org.example.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.constant.Common;
import org.example.constant.ErrorMessage;
import org.example.constant.SuccessfulMessage;
import org.example.exception.AuthenticationException;
import org.example.exception.DuplicateEntityException;
import org.example.exception.EmailServiceException;
import org.example.exception.EntityNotFoundException;
import org.example.model.User;
import org.example.service.RegisterService;
import org.example.utils.EmailUtil;
import org.example.utils.PasswordUtil;
import org.example.utils.SceneUtil;
import org.example.utils.UsernameUtil;

public class RegisterController {
    @FXML private TextField fullNameTextField;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private TextField emailTextField;
    @FXML private Button registerButton;
    @FXML private Label lblErrorFullName;
    @FXML private Label lblErrorUsername;
    @FXML private Label lblErrorPassword;
    @FXML private Label lblErrorEmail;
    @FXML private Label lblErrorConfirmPassword;
    @FXML private Label lblSuccessMessage;
    @FXML private Label lblErrorMessage;
    private RegisterService register = new RegisterService();
    @FXML public void handleRegister(ActionEvent event) {
        clearAllErrorLabels();
        String fullName = fullNameTextField.getText().trim();
        String username = usernameTextField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();
        String email = emailTextField.getText().trim();
        if(validateInput(fullName, username, password, confirmPassword, email)) {
            try {
                boolean success = register.registerService(new User(fullName, username, password, email));
                if(success) {
                    SceneUtil.showSuccessfulMessage(Common.SUCCESS_TITLE, Common.SUCCESS_HEADER, SuccessfulMessage.REGISTER_SUCCESSFULLY);
                    SceneUtil.changeScene(event, "/view/login.fxml", "Đăng nhập");
                }
                else
                    SceneUtil.showErrorAlert(Common.ERROR_TITLE, Common.ERROR_HEADER, ErrorMessage.REGISTER_FAILED);
            } catch (DuplicateEntityException e) {
                if(e.getMessage().equals(ErrorMessage.EMAIL_IS_EXIST))
                    lblErrorEmail.setText(e.getMessage());
                else
                    lblErrorUsername.setText(e.getMessage());
            } catch (EntityNotFoundException e) {
                lblErrorMessage.setText(e.getMessage());
            }
        }
    }

    private boolean validateInput(String fullName, String username, String password, String confirmPassword, String email) {
        boolean hasError = false;
        if(fullName.isEmpty()) {
            lblErrorFullName.setText(Common.NOT_NULL_FULL_NAME);
            hasError = true;
        }
        if(username.isEmpty()) {
            lblErrorUsername.setText(Common.NOT_NULL_USERNAME);
            hasError = true;
        }
        else if(!UsernameUtil.checkRegex(username)) {
            lblErrorUsername.setText(ErrorMessage.INVALID_USERNAME);
            hasError = true;
        }
        if(password.isEmpty()) {
            lblErrorPassword.setText(Common.NOT_NULL_PASSWORD);
            hasError = true;
        }
        else if(!PasswordUtil.checkRegex(password)) {
            lblErrorPassword.setText(ErrorMessage.INVALID_PASSWORD);
            hasError = true;
        }
        if(!PasswordUtil.passwordConfirmation(password, confirmPassword)) {
            lblErrorConfirmPassword.setText(ErrorMessage.INCORRECT_CONFIRM_PASSWORD);
            hasError = true;
        }
        if(email.isEmpty()) {
            lblErrorEmail.setText(Common.NOT_NULL_EMAIL);
            hasError = true;
        }
        else if(!EmailUtil.checkRegex(email)) {
            lblErrorEmail.setText(ErrorMessage.INVALID_EMAIL);
            hasError = true;
        }
        return !hasError;
    }

    private void clearAllErrorLabels() {
        lblErrorFullName.setText("");
        lblErrorUsername.setText("");
        lblErrorPassword.setText("");
        lblErrorEmail.setText("");
        lblErrorConfirmPassword.setText("");
        lblSuccessMessage.setText("");
        lblErrorMessage.setText("");
    }
}
