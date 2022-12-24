package org.lambda.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Functionals {

    private static Consumer<String> getPrinter() {
        return str -> System.out.println("✔ " + str.toUpperCase());
    }

    private static Supplier<Double> randomSupplier() {
        return Math::random;
    }

    public static void main(String[] args) {
        System.out.println(randomSupplier().get());

        List<String> list = new ArrayList<>() {{
            add("Apple");
            add("Mango");
            add("Tiger");
            add("Orange");
        }};
        list.forEach(getPrinter());
        getPrinter().accept("FREE TAIWAN");
    }
}
