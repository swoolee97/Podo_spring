package com.eighteen.podo.util;

public class MailUtil {
    private static final int CODE_LENGTH = 6;

    public static String createRandomNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            int n = (int) Math.random() * 10000 % 10;
            sb.append(n);
        }
        return sb.toString();
    }
}
