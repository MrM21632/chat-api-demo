package com.mrm21632.chatapi.utils;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Utility class for securely salting and hashing passwords using PBKDF2. Since this is a demo application, this is
 * sufficient; bear in mind that, for real-world applications, a stronger algorithm like Bcrypt may be preferred,
 * especially if the passwords are highly-sensitive. At the very least, it is recommended to use hundreds of thousands
 * of iterations for PBKDF2 using SHA-2 hashing.
 */
public class PasswordUtils {
    private static final SecureRandom random = new SecureRandom();

    /**
     * Hashes the given password using PBKDF2-HMAC-SHA256, returning the resulting hash and generated salt.
     *
     * @param password The password to encrypt.
     * @return An array containing both the hashed password and the salt used.
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static String[] hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = getSalt();

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        byte[] hash = factory.generateSecret(spec).getEncoded();
        return new String[] {
            new String(hash, StandardCharsets.UTF_8),
            new String(salt, StandardCharsets.UTF_8)
        };
    }

    private static byte[] getSalt() {
        byte[] result = new byte[16];
        random.nextBytes(result);
        return result;
    }
}
