package org.example.service;

import org.example.constant.Common;
import org.example.dao.OtpDAO;
import org.example.constant.ErrorMessage;
import org.example.dao.UserDAO;
import org.example.utils.EmailUtil;
import org.example.utils.OTPUtil;
import org.example.exception.EmailServiceException;
import org.example.exception.EntityNotFoundException;
import org.example.exception.InvalidCredentialsException;
import org.example.exception.InvalidOtpException;
import org.example.model.OTP;
import org.example.model.User;
import org.example.utils.PasswordUtil;
import java.time.LocalDateTime;

public class ChangePasswordService {
    private UserDAO userDAO = new UserDAO();
    private OtpDAO otpDAO = new OtpDAO();

    public int sendToEmail(String username) {
        User user = userDAO.getUserByUsername(username);
        if(user == null)
            throw new EntityNotFoundException(ErrorMessage.USER_NOT_EXIST);
        String otpCode = OTPUtil.generateOTP();
        OTP otp = new OTP(user.getId(), otpCode);
        otpDAO.saveOTP(otp);
        boolean send = EmailUtil.sendEmail(user.getEmail(), Common.SUBJECT_EMAIL, Common.CONTENT_EMAIL + otp.getCode());
        if(!send)
            throw new EmailServiceException(ErrorMessage.SEND_EMAIL_FAILED);
        return user.getId();
    }

    public void verify(int userId, String otpInput) {
        OTP otp = otpDAO.getLatestOTP(userId);
        if(otp == null)
            throw new EntityNotFoundException(ErrorMessage.OTP_IS_NULL);
        if(otp.isUsed())
            throw new InvalidOtpException(ErrorMessage.OTP_IS_USED);
        if(LocalDateTime.now().isAfter(otp.getExpireAt()))
            throw new InvalidOtpException(ErrorMessage.OTP_IS_EXPIRED);
        if(!otp.getCode().equals(otpInput))
            throw new InvalidOtpException(ErrorMessage.INVALID_OTP);
        otpDAO.markOTPUsed(otp.getId());
    }

    public boolean resetPassword(int userId, String newPassword) {
        return userDAO.updatePassword(userId, newPassword);
    }
}
