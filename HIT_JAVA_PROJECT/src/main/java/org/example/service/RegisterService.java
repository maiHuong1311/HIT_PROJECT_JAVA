package org.example.service;

import org.example.constant.Common;
import org.example.constant.ErrorMessage;
import org.example.dao.UserDAO;
import org.example.model.User;
import org.example.utils.EmailUtil;
import org.example.utils.PasswordUtil;

import java.util.regex.Pattern;

public class RegisterService {
    private UserDAO userDAO = new UserDAO();
    public boolean registerService(User user) throws Exception {
        if(user == null)
            throw new Exception(ErrorMessage.OBJECT_USER_IS_NULL);
        if(userDAO.getUserByUsername(user.getUsername()) != null)
            throw new IllegalArgumentException(ErrorMessage.USERNAME_IS_EXIST);
        if(userDAO.getUserByEmail(user.getEmail()) != null)
            throw new IllegalArgumentException(ErrorMessage.EMAIL_IS_EXIST);
        boolean success = userDAO.register(user);
        if (!success) {
            throw new Exception(ErrorMessage.REGISTER_FAILED);
        }
        return true;
    }
}
