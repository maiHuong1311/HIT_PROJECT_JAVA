package org.example.dao;

import org.example.model.Subject;
import org.example.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectDAO {
    public Subject searchSubject(String name) {
        String sql = "SELECT subjectId, name, totalQuestion, totalExercise FROM subject WHERE name = ? OR name like ?";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Subject(
                        rs.getInt("subjectId"),
                        rs.getString("name"),
                        rs.getInt("totalQuestion"),
                        rs.getInt("totalExercise")
                );
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
