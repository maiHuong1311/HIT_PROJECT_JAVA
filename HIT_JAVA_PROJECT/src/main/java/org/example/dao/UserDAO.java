package org.example.dao;

import org.example.model.User;
import org.example.utils.DBConnection;
import org.example.utils.PasswordUtil;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    public User login(String username, String password) {
        String sql = "SELECT id, fullName, username, password, email, role FROM users WHERE username = ?";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String hashedPassword = rs.getString("password");
                if(PasswordUtil.checkPassword(password, hashedPassword)) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("fullName"),
                            rs.getString("username"),
                            hashedPassword,
                            rs.getString("email"),
                            rs.getString("role")
                    );
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean register(User user) {
        String sql = "INSERT INTO users(fullName, username, password, email, role) VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getUsername());
            String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
            ps.setString(3, hashedPassword);
            ps.setString(4, user.getEmail());
            ps.setString(6, user.getRole());
            return ps.executeUpdate() > 0;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getEmailByUsername(String username) {
        String email = null;
        String sql = "SELECT email FROM users WHERE username = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                email = rs.getString("email");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return email;
    }

    public boolean updatePassword(String username, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE username = ?";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            String hashedPassword = PasswordUtil.hashPassword(newPassword);
            ps.setString(1, hashedPassword);
            ps.setString(2, username);
            return ps.executeUpdate() > 0;
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
