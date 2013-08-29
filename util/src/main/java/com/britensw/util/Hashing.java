package com.britensw.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Hashing {

    public static String md5Hash(final String text) {
        return hash(text, "MD5");
    }

    public static String sha512Hash(final String text) {
        return hash(text, "SHA-512");
    }

    public static String hash(final String text, final String algorithm) {
        if (text == null || text.isEmpty()) {
            throw new IllegalStateException("No content to hash");
        }
        if (algorithm == null || algorithm.isEmpty()) {
            throw new IllegalStateException("No hash algorithm provided");
        }
        try {
            final MessageDigest md = MessageDigest.getInstance(algorithm);
            md.reset();
            md.update(text.getBytes());
            return convertToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    private static String convertToHex(final byte[] data) {
        StringBuilder builder = new StringBuilder();
        for (final byte b : data) {
            final String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                builder.append('0');
            }
            builder.append(hex);
        }
        return builder.toString();
    }
}
