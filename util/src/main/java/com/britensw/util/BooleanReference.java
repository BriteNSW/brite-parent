package com.britensw.util;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
public class BooleanReference {

    private boolean value;

    public BooleanReference(boolean value) {
        this.value = value;
    }

    public boolean get() {
        return value;
    }

    public void set(final boolean value) {
        this.value = value;
    }
}
