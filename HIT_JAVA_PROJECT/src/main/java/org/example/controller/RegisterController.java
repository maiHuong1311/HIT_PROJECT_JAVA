package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.constant.Common;
import org.example.constant.ErrorMessage;
import org.example.constant.SuccessfulMessage;
import org.example.model.User;
import org.example.service.RegisterService;
import org.example.utils.EmailUtil;
import org.example.utils.PasswordUtil;

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
    @FXML public void handleRegister() {
        clearAllErrorLabels();
        try {
            String fullName = fullNameTextField.getText().trim();
            String username = usernameTextField.getText().trim();
            String password = passwordField.getText().trim();String confirmPassword = confirmPasswordField.getText().trim();
            String email = emailTextField.getText().trim();
            boolean hasError = false;
            if(fullName.isEmpty()) {
                lblErrorFullName.setText(Common.NOT_NULL_FULL_NAME);
                hasError = true;
            }
            if(username.isEmpty()) {
                lblErrorUsername.setText(Common.NOT_NULL_USERNAME);
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
            if(hasError)
                return;
            boolean success = register.registerService(new User(fullName, username, password, email));
            if(success)
                lblSuccessMessage.setText(SuccessfulMessage.REGISTER_SUCCESSFULLY);
            } catch(IllegalArgumentException e) {
                if(e.getMessage().equals(ErrorMessage.EMAIL_IS_EXIST))
                    lblErrorEmail.setText(e.getMessage());
                else
                    lblErrorUsername.setText(e.getMessage());
            } catch(Exception e) {
                lblErrorMessage.setText(ErrorMessage.REGISTER_FAILED);
            }
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
