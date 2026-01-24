package org.example.dao;

import org.example.model.Role;
import org.example.model.User;
import org.example.utils.DBConnection;
import org.example.utils.PasswordUtil;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    public User login(String username, String password, Role role) {
        String sql = "SELECT id, fullName, username, password, email, role FROM users WHERE username = ? AND role = ?";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, role.name());
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
        String sql = "INSERT INTO users(fullName, username, password, email) VALUES (?, ?, ?, ?)";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getUsername());
            String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
            ps.setString(3, hashedPassword);
            ps.setString(4, user.getEmail());
            return ps.executeUpdate() > 0;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserByUsername(String username) {
        User user = null;
        String sql = "SELECT id, fullName, username, password, email, role FROM users WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("fullName"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }public User getUserByEmail(String email) {
        User user = null;
        String sql = "SELECT id, fullName, username, password, email, role FROM users WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("fullName"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean updatePassword(int userId, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE id = ?";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            String hashedPassword = PasswordUtil.hashPassword(newPassword);
            ps.setString(1, hashedPassword);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
