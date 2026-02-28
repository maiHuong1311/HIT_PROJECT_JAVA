package org.example.model;

import java.time.LocalDateTime;

public class OTP {
    private int id;
    private int userId;
    private String code;
    private LocalDateTime expireAt;
    private boolean isUsed;

    public OTP() {}
    public OTP(int userId, String code) {
        this.userId = userId;
        this.code = code;
        this.expireAt = LocalDateTime.now().plusMinutes(5);
        this.isUsed = false;
    }

    public OTP(int id, int userId, String code, LocalDateTime expireAt, boolean isUsed) {
        this.id = id;
        this.userId = userId;
        this.code = code;
        this.expireAt = expireAt;
        this.isUsed = isUsed;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    public boolean isUsed() {
        return isUsed;
    }
    public void setUsed(boolean used) {
        isUsed = used;
    }
}
