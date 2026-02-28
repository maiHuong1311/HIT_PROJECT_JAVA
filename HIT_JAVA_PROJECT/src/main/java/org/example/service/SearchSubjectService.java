package org.example.service;

import org.example.constant.ErrorMessage;
import org.example.dao.SubjectDAO;
import org.example.exception.EntityNotFoundException;
import org.example.model.Subject;

import java.util.List;

public class SearchSubjectService {
    private SubjectDAO subjectDao = new SubjectDAO();
    public List<Subject> searchSubject(String name) {
        return subjectDao.searchSubject(name);
    }
}
