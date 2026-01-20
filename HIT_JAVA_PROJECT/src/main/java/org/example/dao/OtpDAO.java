package org.example.dao;

import org.example.model.OTP;
import org.example.utils.DBConnection;

import java.sql.*;

public class OtpDAO {
    private int userId;

    public void saveOTP(OTP otp) {
        String sql = "INSERT INTO OTP(userId, code, expireAt, isUsed) VALUES (?, ?, ?, ?)";
        try(Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, otp.getUserId());
            ps.setString(2, otp.getCode());
            ps.setTimestamp(3, Timestamp.valueOf(otp.getExpireAt()));
            ps.setBoolean(4, otp.isUsed());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public OTP getLatestOTP(int userId) {
        String sql = "SELECT id, userId, code, expireAt, isUsed FROM OTP WHERE userId = ? ORDER BY id DESC LIMIT 1";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new OTP(
                    rs.getInt("id"),
                    rs.getInt("userId"),
                    rs.getString("code"),
                    rs.getTimestamp("expireAt").toLocalDateTime(),
                    rs.getBoolean("isUsed")
                );
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void markOTPUsed(int otpId) {
        String sql = "UPDATE OTP SET isUsed = true WHERE id = ?";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, otpId);
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
