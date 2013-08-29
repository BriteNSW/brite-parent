package com.britensw.util;

/**
 * This is used for weld eventing which cannot be parametrized
 * @see {WELD-000819}
 *
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
public class ClassReference {
    private final Class clazz;

    public ClassReference(final Class clazz) {
        this.clazz = clazz;
    }

    public Class get() {
        return clazz;
    }
}
