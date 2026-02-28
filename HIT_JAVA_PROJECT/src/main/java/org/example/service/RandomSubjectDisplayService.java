package org.example.service;

import org.example.dao.SubjectDAO;
import org.example.model.Subject;

import java.util.List;

public class RandomSubjectDisplayService {
    private SubjectDAO subjectDao = new SubjectDAO();
    public List<Subject> randomSubject() {
        return subjectDao.randomSunject(3);
    }
}
