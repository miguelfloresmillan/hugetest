package com.mgl.test.hugetest.utils;

public class NumberUtils {

    public static float formatDecimal(float amount, int numberDecimals) {
        String value = String.format(java.util.Locale.US, "%." + numberDecimals + "f", amount);
        return Float.valueOf(value);
    }

}
