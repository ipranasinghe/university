package com.project.ums.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Utils {
    public static <T> List<T> convertSetToList(Set<T> set)
    {
        // create an empty list
        List<T> list = new ArrayList<>();

        // push each element in the set into the list
        for (T t : set)
            list.add(t);

        // return the list
        return list;
    }
}
