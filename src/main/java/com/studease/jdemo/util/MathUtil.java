package com.studease.jdemo.util;

/**
 * Author: liushaoping
 * Date: 2016/5/9.
 */
public class MathUtil {
    public static int add(int x, int y) {
        long result = x + y;
        if (result > Integer.MAX_VALUE) {
            throw new ArithmeticException("out of range");
        }
        return (int) result;
    }
}
