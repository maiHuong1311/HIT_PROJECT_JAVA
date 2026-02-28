package org.example.service;

import org.example.dao.QuestionDAO;
import org.example.model.Exercise;
import org.example.model.MultipleChoiceQuestion;
import org.example.model.Subject;
import org.example.model.dto.HistoryQuestion;

import java.util.List;

public class QuestionService {
    private QuestionDAO questionDao = new QuestionDAO();

    public List<MultipleChoiceQuestion> getAllMultipleChoiceQuestion(int lessonId, int userId) {
        return questionDao.getAllMultipleChoiceQuestion(lessonId, userId);
    }

    public List<Exercise> getAllExercise(int lessonId, int userId) {
        return questionDao.getAllExercise(lessonId, userId);
    }

    public int getCompletedMultipleChoiceQuestion(int userId, int lessonId) {
        return questionDao.getCompletedMultipleQuestion(userId, lessonId);
    }

    public int getCompletedExercise(int userId, int lessonId) {
        return questionDao.getCompletedExercise(userId, lessonId);
    }

    public boolean checkAnswer(int questionId, String chosenAnswer) {
        return questionDao.checkAnswer(questionId, chosenAnswer);
    }

    public String getCorrectAnswer(int questionId) {
        return questionDao.getCorrectAnswer(questionId);
    }

    public void saveUserProgress(int userId, int lessonId, int questionId, int isComplete, int isCorrect, String userAnswer, String correctAnswer) {
        questionDao.saveUserProgress(userId, lessonId, questionId, isComplete, isCorrect, userAnswer, correctAnswer);
    }

    public boolean hasProgress(int userId, int lessonId) {
        return questionDao.hasProgress(userId, lessonId);
    }

    public void resetLessonProgress(int userId, int lessonId) {
        questionDao.resetLessonProgress(userId, lessonId);
    }

    public List<Subject> getStudiedSubject(int userId) {
        return questionDao.getStudiedSubjects(userId);
    }

    public List<HistoryQuestion> getStudiedQuestionList(int userId, int lessonId) {
        return questionDao.getStudiedQuestionList(userId, lessonId);
    }
}
