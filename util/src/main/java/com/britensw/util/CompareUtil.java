package com.britensw.util;

public class CompareUtil {

    public static <T extends Comparable<? super T>> int compare(final T a, final T b) {
        if (a == null) {
            if (b == null) {
                return 0;
            } else {
                return -1;
            }
        } else {
            if (b == null) {
                return 1;
            } else {
                return a.compareTo(b);
            }
        }
    }
}
