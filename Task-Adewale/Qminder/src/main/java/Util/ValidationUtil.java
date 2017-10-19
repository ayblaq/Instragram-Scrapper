package Util;

public class ValidationUtil {

    public static void validatePropertyNotNull(Object object, String name) throws IllegalArgumentException{
        if (object == null) throw new IllegalArgumentException(String.format("Parameter '%s' cannot be null", name));
    }

    public static void validatePropertyNotNullOrEmpty(String property, String name) throws IllegalArgumentException{
        validatePropertyNotNull(property, name);
        if (property.trim().isEmpty())
            throw new IllegalArgumentException(String.format("Parameter '%s' cannot be empty", name));
    }

}
