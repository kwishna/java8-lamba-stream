package org.lambda.impl;

import org.lambda.interfaces.StringOperations;

public class StringOpsMain {

    private static final StringOperations UPPER = String::toUpperCase;
    private static final StringOperations LOWER = String::toLowerCase;
    private static final StringOperations REVERSE = (str) -> new StringBuilder(str).reverse().toString();
    private static final StringOperations REMOVE_SPECIAL = (str) -> str.replaceAll("\\W", "-");

    public static void main(String[] args) {

        String str = "This Is #Special String Te5t";
        System.out.println(UPPER.accept(str));
        System.out.println(LOWER.accept(str));
        System.out.println(REVERSE.accept(str));
        System.out.println(REMOVE_SPECIAL.accept(str));

    }
}
