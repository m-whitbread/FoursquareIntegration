package uk.co.markomeara.whitbread.util;

import android.support.annotation.Nullable;

class ValidationUtils {

    private ValidationUtils() {
        throw new AssertionError();
    }

    public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        }
        return reference;
    }

    public static <T> T checkNotNull(T reference) {
        return checkNotNull(reference, null);
    }
}