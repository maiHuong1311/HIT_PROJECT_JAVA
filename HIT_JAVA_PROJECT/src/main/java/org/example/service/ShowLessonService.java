package org.example.service;

import org.example.dao.LessonDAO;
import org.example.model.Lesson;

import java.util.List;

public class ShowLessonService {
    private LessonDAO lessonDao = new LessonDAO();
    public List<Lesson> showLesson(int subjectId) {
        return lessonDao.getLesson(subjectId);
    }
}
