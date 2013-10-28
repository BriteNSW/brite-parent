package com.britensw.util;

import java.util.List;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
public class ListUtil {

    public static <T> T firstOrNull(final List<T> that) {
        return that == null || that.isEmpty() ? null : that.get(0);
    }
}
