package org.example.service;

import org.example.constant.Common;
import org.example.constant.ErrorMessage;
import org.example.dao.OtpDAO;
import org.example.dao.UserDAO;
import org.example.model.OTP;
import org.example.model.User;
import org.example.utils.EmailUtil;
import org.example.utils.OTPUtil;
import java.time.LocalDateTime;

public class ChangePasswordService {
    private UserDAO userDAO = new UserDAO();
    private OtpDAO otpDAO = new OtpDAO();
    public int sendToEmail(String username) throws Exception {
        User user = userDAO.getUserByUsername(username);
        if(user == null)
            throw new Exception(ErrorMessage.USER_NOT_EXIST);
        String otpCode = OTPUtil.generateOTP();
        OTP otp = new OTP(user.getId(), otpCode);
        otpDAO.saveOTP(otp);
        boolean send = EmailUtil.sendEmail(user.getEmail(), Common.SUBJECT_EMAIL, Common.CONTENT_EMAIL + otp);
        if(!send)
            throw new Exception(ErrorMessage.SEND_EMAIL_FAILED);
        return user.getId();
    }

    public boolean verify(int userId, String inputOTP) throws Exception {
        OTP otp = otpDAO.getLatestOTP(userId);
        if(otp == null)
            throw new Exception(ErrorMessage.OTP_NOT_EXIST);
        if(otp.isUsed())
            throw new Exception(ErrorMessage.OTP_IS_USED);
        if(!otp.getCode().equals(inputOTP))
            throw new Exception(ErrorMessage.INVALID_OTP);
        if(LocalDateTime.now().isAfter(otp.getExpireAt()))
            throw new Exception(ErrorMessage.OTP_IS_EXPIRED);
        otpDAO.markOTPUsed(otp.getId());
        return true;
    }

    public boolean resetPassword(int userId, String otpInput, String newPassword) throws Exception {
        boolean isValid = verify(userId, otpInput);
        if(isValid) {
            userDAO.updatePassword(userId, newPassword);
            return true;
        }
        return false;
    }
}
