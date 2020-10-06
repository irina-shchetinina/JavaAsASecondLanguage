package io.github.javaasasecondlanguage.homework02.di;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContextTest {

    @Test
    void registerNullObject() {
        assertThrows(IllegalArgumentException.class, () -> new Context().register(null));
    }

    @Test
    void registerNullObjectWithQualifier() {
        assertThrows(IllegalArgumentException.class,
            () -> new Context().register(null, "null object"));
    }

    @Test
    void registerNullQualifier() {
        assertThrows(IllegalArgumentException.class,
            () -> new Context().register("my object", null));
    }
}