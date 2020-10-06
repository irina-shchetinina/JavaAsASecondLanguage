package io.github.javaasasecondlanguage.homework02.di;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Context {

    static final List<Object> context = new ArrayList<>();
    static final Map<String, Object> qContext = new HashMap<>();

    // TODO it is strange decision, but now it is: if Someone create new Context
    // all previous registered objects will be removed
    public Context() {
        context.clear();
        qContext.clear();
    }

    public <T> Context register(T object, String qualifier) {
        if (qualifier == null) {
            throw new IllegalArgumentException("Context qualifier should be not null");
        }
        if (object == null) {
            throw new IllegalArgumentException("Context object should be not null");
        }
        qContext.put(qualifier, object);
        register(object);
        return this;
    }

    public <T> Context register(T object) {
        if (object == null) {
            throw new IllegalArgumentException("Context object should be not null");
        }
        final int index = context.indexOf(object);
        if (index != -1) {
            context.remove(index);
        }
        context.add(object);
        return this;
    }
}
