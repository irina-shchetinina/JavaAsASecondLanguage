package io.github.javaasasecondlanguage.homework02.di;

public final class Injector {

    private Injector() {
    }

    public static <T> T inject(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Class for inject should be not null");
        }

        final Object[] objects = Context.context.stream()
                .filter(object -> isObjectCastToClass(object, clazz))
                .toArray();

        if (objects.length > 1) {
            throw new RuntimeException("Too many object found");
        }

        if (objects.length == 0) {
            throw new RuntimeException("Can't find object");
        }

        return (T) objects[0];
    }

    public static <T> T inject(Class<T> clazz, String qualifier) {
        if (clazz == null) {
            throw new IllegalArgumentException("Class should be not null");
        }

        if (qualifier == null) {
            throw new IllegalArgumentException("Qualifier should be not null");
        }

        final Object obj = Context.qContext.get(qualifier);
        if (obj == null) {
            throw new RuntimeException("Can't find object with qualifier %s".formatted(qualifier));
        }
        if (isObjectCastToClass(obj, clazz)) {
            return (T) obj;
        }

        throw new RuntimeException("Qualifier %s is not good for this class".formatted(qualifier));
    }

    private static boolean isObjectCastToClass(Object object, Class clazz) {
        return clazz.isAssignableFrom(object.getClass());
    }
}
