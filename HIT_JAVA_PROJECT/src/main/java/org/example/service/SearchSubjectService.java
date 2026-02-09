package org.example.service;

import org.example.constant.ErrorMessage;
import org.example.dao.SubjectDAO;
import org.example.exception.EntityNotFoundException;
import org.example.model.Subject;

public class SearchSubjectService {
    private SubjectDAO subjectDao = new SubjectDAO();
    public boolean searchSubject(String name) {
        Subject subject = subjectDao.searchSubject(name);
        if(subject == null) {
            throw new EntityNotFoundException(ErrorMessage.SEARCH_FAILED);
        }
        return true;
    }
}
