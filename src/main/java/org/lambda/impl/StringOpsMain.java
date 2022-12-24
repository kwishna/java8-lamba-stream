package org.lambda.impl;

import org.lambda.interfaces.StringOperations;

public class StringOpsMain {

    private static StringOperations upper = String::toUpperCase;
    private static StringOperations lower = String::toLowerCase;
    private static StringOperations reverse = (str) -> new StringBuilder(str).reverse().toString();
    private static StringOperations removeSpecial = (str) -> str.replaceAll("\\W", "-");

    public static void main(String[] args) {

        String str = "This Is #Special String Te5t";
        System.out.println(upper.accept(str));
        System.out.println(lower.accept(str));
        System.out.println(reverse.accept(str));
        System.out.println(removeSpecial.accept(str));

    }
}
