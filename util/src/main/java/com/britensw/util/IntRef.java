package com.britensw.util;

/**
 * Holds an int. Used so we can parse and rollback
 */
public class IntRef {
    private int value;

    public IntRef(int value) {
        this.value = value;
    }

    public int decrementAndGet() {
        return --value;
    }

    public int incrementAndGet() {
        return ++value;
    }

    public int get() {
        return value;
    }

    public void set(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
