package com.pragma.traceability.domain.util;

public final class DomainConstants {
    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public enum Field {
        NAME,
        DESCRIPTION


    }

    public static final int MAXIMUM_LENGTH_NAME_CATEGORY = 50;
    public static final int MINIMUM_CATEGORIES = 1;
    public static final int MAXIMUM_CATEGORIES = 3;
    public static final int MAXIMUM_LENGTH_NAME_MARK = 50;
    public static final int MAXIMUM_LENGTH_DESCRIPTION_CATEGORY = 50;
    public static final int MAXIMUM_LENGTH_DESCRIPTION_MARK = 50;
}
