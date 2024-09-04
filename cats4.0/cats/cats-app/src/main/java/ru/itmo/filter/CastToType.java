package ru.itmo.filter;

import java.util.ArrayList;
import java.util.List;

public class CastToType {
    public static Object castToType(String value, Class type) {
        if (Enum.class.isAssignableFrom(type)) {
            return Enum.valueOf(type, value);
        } else if (Integer.class.isAssignableFrom(type)) {
            return Integer.valueOf(value);
        }
        return value;
    }

    public static Object castToType(List<String> value, Class type) {
        List<Object> list = new ArrayList<>();
        for (String val : value) {
            list.add(castToType(val, type));
        }
        return list;
    }
}
