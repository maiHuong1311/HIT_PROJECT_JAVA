package org.example.service;

import org.example.constant.ErrorMessage;
import org.example.dao.UserDAO;
import org.example.exception.DuplicateEntityException;
import org.example.exception.EntityNotFoundException;
import org.example.exception.AuthenticationException;
import org.example.model.User;

public class RegisterService {
    private UserDAO userDAO = new UserDAO();
    public void registerService(User user) {
        if(user == null)
            throw new EntityNotFoundException(ErrorMessage.SYSTEM_ERROR);
        if(userDAO.getUserByUsername(user.getUsername()) != null)
            throw new DuplicateEntityException(ErrorMessage.USERNAME_IS_EXIST);
        if(userDAO.getUserByEmail(user.getEmail()) != null)
            throw new DuplicateEntityException(ErrorMessage.EMAIL_IS_EXIST);
        boolean success = userDAO.register(user);
        if(!success) {
            throw new AuthenticationException(ErrorMessage.REGISTER_FAILED);
        }
    }
}
