package org.example.controller;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.example.constant.Common;
import org.example.constant.ErrorMessage;
import org.example.constant.SuccessfulMessage;
import org.example.exception.EmailServiceException;
import org.example.exception.EntityNotFoundException;
import org.example.exception.InvalidOtpException;
import org.example.service.ChangePasswordService;
import org.example.utils.PasswordUtil;
import org.example.utils.SceneUtil;

public class ChangePasswordController {
    @FXML private TextField usernameTextField;
    @FXML private TextField OTPTextField;
    @FXML private PasswordField newPasswordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label lblErrorUsername;
    @FXML private Label lblErrorOTP;
    @FXML private Label lblErrorNewPassword;
    @FXML private Label lblErrorConfirmPassword;
    @FXML private Label lblSuccessfulMessage;
    @FXML private Button confirmButton;
    @FXML private Button backButton;
    @FXML private Button sendingCodeButton;
    @FXML private Button switchPasswordButton;
    @FXML private VBox vBoxPassword;
    private ChangePasswordService changePasswordService = new ChangePasswordService();
    private int userId = -1;
    public void initialize() {
        vBoxPassword.setVisible(false);
        vBoxPassword.setManaged(false);
        switchPasswordButton.setVisible(false);
    }
    @FXML public void handleSendEmail(ActionEvent event) {
        clearAllErrorLabels();
        String username = usernameTextField.getText().trim();
        if(username.isEmpty())
            lblErrorUsername.setText(Common.NOT_NULL_USERNAME);
        else {
            try {
                userId = changePasswordService.sendToEmail(username);
                lblSuccessfulMessage.setText(SuccessfulMessage.SEND_OTP_SUCCESSFULLY);
                hideSendingCodeButton();
            } catch(EntityNotFoundException e) {
                lblErrorUsername.setText(e.getMessage());
            } catch(EmailServiceException e) {
                lblErrorUsername.setText(e.getMessage());
            }
        }
    }

    @FXML
    public void handleVerifyOtp(ActionEvent event) {
        clearAllErrorLabels();
        String otpCode = OTPTextField.getText().trim();
        if(otpCode.isEmpty()) {
            lblErrorOTP.setText(Common.NOT_NULL_OTP);
        } else {
            try {
                changePasswordService.verify(userId, otpCode);
                showPasswordFields();
                lblSuccessfulMessage.setText("");
                OTPTextField.setEditable(false);
                usernameTextField.setEditable(false);
                sendingCodeButton.setDisable(true);
                backButton.setVisible(false);
            } catch(EntityNotFoundException e) {
                lblErrorUsername.setText(e.getMessage());
            } catch(InvalidOtpException e) {
                lblErrorOTP.setText(e.getMessage());
            }
        }
    }

    @FXML void backToLogin(ActionEvent event) {
        SceneUtil.changeScene(event, "/view/login.fxml", "Đăng nhập");
    }

    @FXML
    public void handleChangePassword(ActionEvent event) {
        String newPassword = newPasswordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();
        if (validateInputPassword(newPassword, confirmPassword)) {
            boolean success = changePasswordService.resetPassword(userId, newPassword);
            if (success) {
                SceneUtil.showSuccessfulMessage(Common.SUCCESS_TITLE, Common.SUCCESS_HEADER, SuccessfulMessage.CHANGE_PASSWORD_SUCCESSFULLY);
                SceneUtil.changeScene(event, "/view/login.fxml", "Đăng nhập");
            } else {
                SceneUtil.showErrorAlert(Common.ERROR_TITLE, Common.ERROR_HEADER, ErrorMessage.CHANGE_PASSWORD_FAILED);
            }
        }
    }

    @FXML
    private boolean validateInputPassword(String newPassword, String confirmPassword) {
        boolean hasError = false;
        if(newPassword.isEmpty()) {
            lblErrorNewPassword.setText(Common.NOT_NULL_PASSWORD);
            hasError = true;
        }
        else if(!PasswordUtil.checkRegex(newPassword)) {
            lblErrorNewPassword.setText(ErrorMessage.INVALID_PASSWORD);
            hasError = true;
        }
        if(!PasswordUtil.passwordConfirmation(newPassword, confirmPassword)) {
            lblErrorConfirmPassword.setText(ErrorMessage.INCORRECT_CONFIRM_PASSWORD);
            hasError = true;
        }
        return !hasError;
    }

    @FXML
    private void clearAllErrorLabels() {
        lblErrorUsername.setText("");
        lblErrorOTP.setText("");
        lblErrorNewPassword.setText("");
        lblErrorConfirmPassword.setText("");
    }

    @FXML
    private void showPasswordFields() {
        switchPasswordButton.setVisible(true);
        vBoxPassword.setVisible(true);
        vBoxPassword.setManaged(true);
    }

    @FXML
    private void hideSendingCodeButton() {
        sendingCodeButton.setDisable(true);
        PauseTransition pause = new PauseTransition(Duration.seconds(20));
        pause.setOnFinished(events -> sendingCodeButton.setDisable(false));
        pause.play();
    }
}
