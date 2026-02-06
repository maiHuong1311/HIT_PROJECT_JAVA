package org.example.utils;

import java.util.regex.Pattern;

public class UsernameUtil {
    private static final String USERNAME_PATTERN = "^[a-zA-Z]{9,}$";
    public static boolean checkRegex(String username) {
        return Pattern.compile(USERNAME_PATTERN).matcher(username).matches();
    }
}
