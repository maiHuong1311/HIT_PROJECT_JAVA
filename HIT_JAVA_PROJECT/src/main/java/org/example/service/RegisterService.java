package org.example.service;

import org.example.constant.Common;
import org.example.constant.ErrorMessage;
import org.example.dao.UserDAO;
import org.example.model.User;

import java.util.regex.Pattern;

public class RegisterService {
    private UserDAO userDAO = new UserDAO();
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z]).{8,}$";
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    public boolean registerService(User user) {
        if (user.getUsername() == null)
            throw new IllegalArgumentException(Common.NOT_NULL_USERNAME);
        if (user.getPassword() == null)
            throw new IllegalArgumentException(Common.NOT_NULL_PASSWORD);
        if (user.getFullName() == null)
            throw new IllegalArgumentException(Common.NOT_NULL_FULL_NAME);
        if (user.getEmail() == null)
            throw new IllegalArgumentException(Common.NOT_NULL_EMAIL);
        if(!Pattern.compile(EMAIL_PATTERN).matcher(user.getEmail()).matches())
            throw new IllegalArgumentException(ErrorMessage.INVALID_EMAIL);
        if(!Pattern.compile(PASSWORD_PATTERN).matcher(user.getPassword()).matches())
            throw new IllegalArgumentException(ErrorMessage.INVALID_PASSWORD);
        if(userDAO.getUserByUsername(user.getUsername()) != null)
            throw new IllegalArgumentException(ErrorMessage.USERNAME_IS_EXIST);
        if(!userDAO.register(user))
            throw new RuntimeException(ErrorMessage.REGISTER_FAILED);
        return true;
    }
}
