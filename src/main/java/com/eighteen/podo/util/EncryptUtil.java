package com.eighteen.podo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptUtil {

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String bcrypt(String password) {
        return encoder.encode(password);
    }

    public static boolean isMatch(String password, String cryptedPassword) {
        return encoder.matches(password, cryptedPassword);
    }

}
