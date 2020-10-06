package io.github.javaasasecondlanguage.homework02.di;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Context {

    static final List<Object> context = new ArrayList<>();
    static final Map<String, Object> qContext = new HashMap<>();

    public <T> Context register(T object, String qualifier) {
        qContext.put(qualifier, object);
        register(object);
        return this;
    }

    public <T> Context register(T object) {
        final int index = context.indexOf(object);
        if (index != -1) {
            context.remove(index);
        }
        context.add(object);
        return this;
    }
}
