package org.example.dao;

import org.example.model.*;
import org.example.model.dto.HistoryQuestion;
import org.example.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {
    public List<MultipleChoiceQuestion> getAllMultipleChoiceQuestion(int lessonId, int userId) {
        List<MultipleChoiceQuestion> multipleQuestionList = new ArrayList<>();
        String sql = "SELECT q.questionId, q.title, uqs.isComplete, o.content FROM question q JOIN multiple_choice_option o on q.questionId = o.questionId LEFT JOIN user_question_status uqs ON q.questionId = uqs.questionId AND uqs.id = ? WHERE q.lessonId = ? AND q.questionType = 'MULTIPLECHOICE' ORDER BY q.questionId";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, userId);
            ps.setInt(2, lessonId);
            ResultSet rs = ps.executeQuery();
            MultipleChoiceQuestion multipleChoiceQuestion = null;
            while(rs.next()) {
                int questionId = rs.getInt("questionId");
                if(multipleChoiceQuestion == null || multipleChoiceQuestion.getQuestionId() != questionId) {
                    multipleChoiceQuestion = new MultipleChoiceQuestion();
                    multipleChoiceQuestion.setQuestionId(questionId);
                    multipleChoiceQuestion.setTitle(rs.getString("title"));
                    multipleChoiceQuestion.setComplete(rs.getInt("isComplete") == 1);
                    multipleChoiceQuestion.setAnswer(new ArrayList<>());
                    multipleQuestionList.add(multipleChoiceQuestion);
                }
                String answer = rs.getString("content");
                if(answer != null) {
                    multipleChoiceQuestion.getAnswer().add(answer);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return multipleQuestionList;
    }

    public List<Exercise> getAllExercise(int lessonId, int userId) {
        List<Exercise> exerciseList = new ArrayList<>();
        String sql = "SELECT q.questionId, q.title, uqs.isComplete, e.exampleAnswer FROM question q LEFT JOIN exercise_detail e ON q.questionId = e.questionId LEFT JOIN user_question_status uqs ON q.questionId = uqs.questionId AND uqs.id = ? WHERE q.lessonId = ? AND q.questionType = 'EXERCISE'";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, lessonId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Exercise exercise = new Exercise();
                exercise.setQuestionId(rs.getInt("questionId"));
                exercise.setTitle(rs.getString("title"));
                exercise.setComplete(rs.getInt("isComplete") == 1);
                exercise.setExampleAnswer(rs.getString("exampleAnswer"));
                exerciseList.add(exercise);
            }
        } catch(SQLException e ) {
            e.printStackTrace();
        }
        return exerciseList;
    }

    public void saveUserProgress(int userId, int questionId, int lessonId, int isComplete, int isCorrect, String userAnswer, String correctAnswer) {
        String sql = "INSERT INTO user_question_status(id, questionId, lessonId, isComplete, isCorrect, userAnswer, correctAnswer) VALUES (?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE isComplete = VALUES(isComplete), isCorrect = VALUES(isCorrect), userAnswer = VALUES(userAnswer), correctAnswer = VALUES(correctAnswer)";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, userId);
            ps.setInt(2, questionId);
            ps.setInt(3, lessonId);
            ps.setInt(4, isComplete);
            ps.setInt(5, isCorrect);
            ps.setString(6, userAnswer);
            ps.setString(7, correctAnswer);
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public int getCompletedMultipleQuestion(int userId, int lessonId) {
        String sql = "SELECT COUNT(*) FROM user_question_status uqs JOIN question q ON uqs.questionId = q.questionId WHERE q.lessonId = ? AND uqs.id = ? AND questionType = 'MULTIPLECHOICE' AND uqs.isComplete = 1";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, lessonId);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                return rs.getInt(1);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getCompletedExercise(int userId, int lessonId) {
        String sql = "SELECT COUNT(*) FROM user_question_status uqs JOIN question q ON uqs.questionId = q.questionId WHERE q.lessonId = ? AND uqs.id = ? AND questionType = 'EXERCISE' AND uqs.isComplete = 1";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, lessonId);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                return rs.getInt(1);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean checkAnswer(int questionId, String chosenAnswer) {
        String sql = "SELECT isCorrect FROM multiple_choice_option WHERE questionId = ? AND content = ?";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, questionId);
            ps.setString(2, chosenAnswer);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                return rs.getBoolean("isCorrect");
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void resetLessonProgress(int userId, int lessonId) {
        String sql = "DELETE FROM user_question_status WHERE id = ? AND lessonId = ?";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, userId);
            ps.setInt(2, lessonId);
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public String getCorrectAnswer(int questionId) {
        String sql = "SELECT content FROM multiple_choice_option WHERE questionId = ? AND isCorrect = 1";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, questionId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("content");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean hasProgress(int userId, int lessonId) {
        String sql = "SELECT COUNT(*) FROM user_question_status WHERE id = ? AND lessonId = ? AND isComplete = 1";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, userId);
            ps.setInt(2, lessonId);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                return rs.getInt(1) > 0;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Subject> getStudiedSubjects(int userId) {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT DISTINCT s.subjectId, s.name, s.totalQuestion, s.totalExercise FROM user_question_status uqs JOIN lesson l ON uqs.lessonId = l.lessonId JOIN subject s ON l.subjectId = s.subjectId WHERE uqs.id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectId(rs.getInt("subjectId"));
                s.setName(rs.getString("name"));
                s.setTotalQuestion(rs.getInt("totalQuestion"));
                s.setTotalExercise(rs.getInt("totalExercise"));
                subjects.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    public List<HistoryQuestion> getStudiedQuestionList(int userId, int lessonId) {
        List<HistoryQuestion> studiedList = new ArrayList<>();
        String sql = "SELECT q.title, uqs.userAnswer, uqs.correctAnswer FROM question q JOIN user_question_status uqs ON q.questionId = uqs.questionId WHERE uqs.id = ? AND uqs.lessonId = ? AND uqs.isComplete = 1";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, lessonId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                HistoryQuestion hq = new HistoryQuestion();
                hq.setTitle(rs.getString("title"));
                hq.setUserAnswer(rs.getString("userAnswer"));
                hq.setCorrectAnswer(rs.getString("correctAnswer"));
                studiedList.add(hq);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return studiedList;
    }
}
