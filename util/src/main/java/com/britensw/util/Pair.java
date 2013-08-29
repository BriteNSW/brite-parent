package com.britensw.util;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
public class Pair<K,V> {
    final K k;
    final V v;

    public Pair(final K k, final V v) {
        this.k = k;
        this.v = v;
    }

    public K getK() {
        return k;
    }

    public V getV() {
        return v;
    }
}
