package com.britensw.util;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtil {

    public static <T> List<T> sortableSingletonList(T that) {
        final ArrayList<T> list = new ArrayList<T>(1);
        list.add(that);
        return list;
    }
}
