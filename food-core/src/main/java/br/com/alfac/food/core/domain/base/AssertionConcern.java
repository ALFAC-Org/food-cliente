package br.com.alfac.food.core.domain.base;

public class AssertionConcern {

    public static void assertArgumentTrue(boolean assertion, String message) {
        if (!assertion) {
            throw new DomainException(message);
        }
    }

    public static void assertArgumentFalse(boolean assertion, String message) {
        if (assertion) {
            throw new DomainException(message);
        }
    }

    public static void assertArgumentNotNull(Object object, String message) {
        if (object == null) {
            throw new DomainException(message);
        }
    }

    public static void assertArgumentNotEmpty(String value, String message) {
        if (value == null || value.trim().isEmpty()) {
            throw new DomainException(message);
        }
    }

    public static void assertArgumentLength(String value, int maxLength, String message) {
        assertArgumentNotNull(value, message);
        if (value.length() > maxLength) {
            throw new DomainException(message);
        }
    }

    public static void assertArgumentLength(String value, int minLength, int maxLength, String message) {
        assertArgumentNotNull(value, message);
        int length = value.length();
        if (length < minLength || length > maxLength) {
            throw new DomainException(message);
        }
    }
}
