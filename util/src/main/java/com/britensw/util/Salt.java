package com.britensw.util;

import java.security.SecureRandom;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
public final class Salt {

    public static String generate() {
        final SecureRandom random = new SecureRandom();
        random.setSeed(System.nanoTime());
        return Hashing.sha512Hash(random.toString());
    }
}
