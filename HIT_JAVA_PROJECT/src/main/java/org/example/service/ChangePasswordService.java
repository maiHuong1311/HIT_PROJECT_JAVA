package org.example.service;

import org.example.constant.Common;
import org.example.constant.ErrorMessage;
import org.example.dao.UserDAO;
import org.example.utils.EmailUtil;
import org.example.utils.OTPUtil;

public class ChangePasswordService {
    private UserDAO userDAO = new UserDAO();

    public String sendToEmail(String username) throws Exception {
        String email = userDAO.getEmailByUsername(username);
        if(email == null)
            throw new Exception(ErrorMessage.USERNAME_NOT_EXIST);
        String otp = OTPUtil.generateOTP();
        boolean send = EmailUtil.sendEmail(email, Common.SUBJECT_EMAIL, Common.CONTENT_EMAIL + otp);
        if(!send)
            throw new Exception(ErrorMessage.SEND_EMAIL_FAILED);
        return otp;
    }
}
