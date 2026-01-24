package org.example.service;

import org.example.constant.Common;
import org.example.constant.ErrorMessage;
import org.example.dao.UserDAO;
import org.example.model.Role;
import org.example.model.User;

public class LoginService {
    private UserDAO userDAO = new UserDAO();
    public static User currentUser;
    public boolean loginService(String username, String password, Role role) {
        User user = userDAO.login(username, password, role);
        if(user == null)
            throw new IllegalArgumentException(ErrorMessage.LOGIN_FAILED);
        currentUser = user;
        return true;
    }
}
