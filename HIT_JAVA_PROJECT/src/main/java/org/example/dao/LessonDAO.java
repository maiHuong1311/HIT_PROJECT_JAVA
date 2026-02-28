package org.example.dao;

import org.example.model.Lesson;
import org.example.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonDAO {
    public List<Lesson> getLesson(int subjectId) {
        String sql = "SELECT lessonId, subjectId, title, totalQuestion, totalExercise FROM lesson WHERE subjectId = ?";
        List<Lesson> lessonList = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, subjectId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Lesson lesson = new Lesson(
                        rs.getInt("lessonId"),
                        rs.getInt("subjectId"),
                        rs.getString("title"),
                        rs.getInt("totalQuestion"),
                        rs.getInt("totalExercise")
                );
                lessonList.add(lesson);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return lessonList;
    }
}
