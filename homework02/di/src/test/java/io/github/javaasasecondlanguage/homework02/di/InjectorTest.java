package io.github.javaasasecondlanguage.homework02.di;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InjectorTest {

    public static final int EXPECTED_INT = 345;
    public static final ArrayList<Integer> EXPECTED_ARRAY_LIST = new ArrayList<>(List.of(1, 2, 3));
    private Context context;

    @BeforeEach
    void setup() {
        context = new Context()
                .register(true)
                .register("string with qualifier", "simpleString")
                .register("string with  other qualifier", "otherString")
                .register(EXPECTED_INT, "integerWithQualifier")
                .register(EXPECTED_ARRAY_LIST)
                .register(EXPECTED_ARRAY_LIST)
        ;
    }

    @Test
    void simpleInject() {
        Boolean boolValue = Injector.inject(Boolean.class);
        assertEquals(true, boolValue);

        String simpleString = Injector.inject(String.class, "simpleString");
        assertEquals("string with qualifier", simpleString);

        int simpleInt = Injector.inject(Integer.class);
        assertEquals(EXPECTED_INT, simpleInt);

        ArrayList<Integer> array = Injector.inject(ArrayList.class);
        assertEquals(EXPECTED_ARRAY_LIST, array);
    }

    @Test
    void injectForSuper() {
        Number number = Injector.inject(Integer.class);
        assertEquals(EXPECTED_INT, number);
    }

    @Test
    void injectForInterfaceSimple() {
        List<Integer> list = Injector.inject(ArrayList.class);
        assertEquals(EXPECTED_ARRAY_LIST, list);
    }

    @Test
    void injectForInterface() {
        List<Integer> list = Injector.inject(List.class);
        assertEquals(EXPECTED_ARRAY_LIST, list);
    }

    @Test
    void injectNull() {
        assertThrows(IllegalArgumentException.class, () -> Injector.inject(null));
        assertThrows(IllegalArgumentException.class, () -> Injector.inject(String.class, null));
        assertThrows(IllegalArgumentException.class, () -> Injector.inject(null, "simpleString"));
        assertThrows(IllegalArgumentException.class, () -> Injector.inject(null, null));
    }

    @Test
    void tooManyObjects() {
        context.register(EXPECTED_INT + 1);
        assertThrows(RuntimeException.class, () -> Injector.inject(Integer.class));

        assertThrows(RuntimeException.class, () -> Injector.inject(String.class));
    }

    @Test
    void noObjects() {
        assertThrows(RuntimeException.class, () -> Injector.inject(HashMap.class));

        assertThrows(RuntimeException.class,
            () -> Injector.inject(String.class, "nonExistent"));

        assertThrows(RuntimeException.class,
            () -> Injector.inject(Integer.class, "simpleString"));
    }
}