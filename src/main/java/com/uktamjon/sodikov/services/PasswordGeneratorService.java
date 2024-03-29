package com.uktamjon.sodikov.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
@Slf4j
public class PasswordGeneratorService {

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%&*()_+-=[]|,./?><";


    private static final String PASSWORD_ALLOW = CHAR_LOWER + CHAR_UPPER + NUMBER + SPECIAL_CHARS;

    public String generateRandomPassword(int length) {
        if (length < 1) {
            log.trace("Password length is not proper: {}", length);
            throw new IllegalArgumentException("Length must be at least 1");
        }

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(PASSWORD_ALLOW.length());
            password.append(PASSWORD_ALLOW.charAt(randomIndex));
        }
        log.info("Generated some password");
        return password.toString();
    }


    public String encryptPassword(String password) {
        log.info("Encrypting password: {}", password);
        String encryptedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(password.getBytes());

            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            encryptedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encryptedPassword;
    }

    public boolean checkPassword(String enteredPassword, String storedEncryptedPassword) {
        log.info("Checking password: {} and {}", enteredPassword, storedEncryptedPassword);
        return enteredPassword.equals(storedEncryptedPassword);
    }


    
}
