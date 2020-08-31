package com.example.enrollment.util;

import java.security.MessageDigest;
import java.security.SecureRandom;

public final class EnrollmentHasher {


    public static String getSecurePassword(String password, byte[] salt) throws Exception {

        String generatedPassword = null;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);
        byte[] bytes = md.digest(password.getBytes());
        generatedPassword = bytesToHex(bytes);

        return generatedPassword;
    }

    public static byte[] getSalt() throws Exception {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;


    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
