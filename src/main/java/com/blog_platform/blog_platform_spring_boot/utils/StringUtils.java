package com.blog_platform.blog_platform_spring_boot.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class StringUtils {
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder().withoutPadding();

    public static boolean isEmpty(String str) {
        return str == null || str.isBlank();
    }

    public static String generateRandomString(int length) {
        byte[] randomBytes = new byte[length];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes).substring(0, length);
    }
}
