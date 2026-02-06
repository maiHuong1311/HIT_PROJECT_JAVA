package org.example.utils;

import org.mindrot.jbcrypt.BCrypt;

import java.util.regex.Pattern;

public class PasswordUtil {
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z]).{8,}$";
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword(String plain, String hashed) {
        return BCrypt.checkpw(plain, hashed);
    }

    public static boolean checkRegex(String password) {
        return Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
    }

    public static boolean passwordConfirmation(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
