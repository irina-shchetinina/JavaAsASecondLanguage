package io.github.javaasasecondlanguage.homework02.di;

public class Injector {
    public static <T> T inject(Class<T> clazz) {
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
        final Object obj = Context.qContext.get(qualifier);
        if (obj == null) {
            //TODO change Exceptions
            throw new RuntimeException("Can't find object with qualifier %s".formatted(qualifier));
        }
        if (isObjectCastToClass(obj, clazz)) {
            return (T) obj;
        }

        //TODO change Exceptions
        throw new RuntimeException("Qualifier %s is not good for this class".formatted(qualifier));
    }

    private static boolean isObjectCastToClass(Object object, Class clazz) {
        return clazz.isAssignableFrom(object.getClass());
    }
}
