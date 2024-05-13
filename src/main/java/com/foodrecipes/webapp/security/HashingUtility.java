package com.foodrecipes.webapp.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashingUtility {

    /**
     * Static class for specific usage: generate hashpassword
     */
    private HashingUtility() {
    }

    /**
     * Generate random salt value
     * 
     * @return bytesToHex(salt) -> formatted HEX value
     */
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return bytesToHex(salt);
    }

    /**
     * Use SHA-256 algorithem to generate encrypted password, requires provided salt
     * value and raw password
     * 
     * @param password
     * @param salt
     * @return bytesToHex(salt) -> formatted HEX value
     * @throws NoSuchAlgorithmException
     */
    public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(hexToBytes(salt)); // Use the provided salt
        byte[] hashedPassword = md.digest(password.getBytes());
        return bytesToHex(hashedPassword);
    }

    /**
     * Method for bytes array convert to Hex string
     * 
     * @param bytes
     * @return HexString
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * Method for Hex string convert to bytes array
     * 
     * @param hexString
     * @return byte[]
     */
    private static byte[] hexToBytes(String hexString) {
        byte[] bytes = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length(); i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return bytes;
    }
}
