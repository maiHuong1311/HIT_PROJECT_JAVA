package org.example.dao;

import org.example.model.Subject;
import org.example.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {
    public List<Subject> searchSubject(String name) {
        String sql = "SELECT subjectId, name, totalQuestion, totalExercise FROM subject WHERE name = ? OR name like ?";
        List<Subject> list = new ArrayList<>();
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Subject s = new Subject(
                        rs.getInt("subjectId"),
                        rs.getString("name"),
                        rs.getInt("totalQuestion"),
                        rs.getInt("totalExercise")
                );
                list.add(s);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Subject> randomSunject(int limit) {
        List<Subject> result = new ArrayList<>();
        String sql = "SELECT subjectId, name, totalQuestion, totalExercise FROM subject ORDER BY RAND() LIMIT ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             ps.setInt(1, limit);
             ResultSet rs = ps.executeQuery();
             while(rs.next()) {
                 Subject subject = new Subject(
                         rs.getInt("subjectId"),
                         rs.getString("name"),
                         rs.getInt("totalQuestion"),
                         rs.getInt("totalExercise")
                 );
                 result.add(subject);
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
