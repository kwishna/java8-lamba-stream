package org.lambda.impl;

import org.lambda.interfaces.FnInterfaceExample;
import org.lambda.interfaces.TriConsumer;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;


class HigherOrderFn {
    public static void modifier(FnInterfaceExample example) {
        System.out.println("------ modify ------");
        System.out.println(example.modify("Apple"));
        System.out.println(example.modify("Mango"));
        System.out.println(example.modify("Tiger"));
        System.out.println(example.modify("Sunflower"));
    }

    public static FnInterfaceExample getReverser() {
        System.out.println("------ getReverser ------");
        return (str) -> new StringBuilder(str).reverse().toString();
    }

    public static FnInterfaceExample lengthFinder() {
        System.out.println("------- lengthFinder -------");
        return (str) -> "" + str.length();
    }

    public static BiConsumer<String, String> getReplacer() {
        System.out.println("-- BiConsumer<String, String> Replace Special Chars --");
        return (str, replacer) -> {
            String replaced = str.replaceAll(replacer, "-");
            System.out.println(replaced);
        };
    }

    public static Predicate<Integer> isGreaterThan(int x) {
        System.out.println("------ isGreaterThan -------");
        return (no) -> no > x;
    }

    public static Predicate<Integer> isLessThan(int x) {
        System.out.println("------ isLessThan -------");
        return (no) -> no < x;
    }
}

public class FnInterfaceMain {
    public static void main(String[] args) {
        // Single Line Lambda
        FnInterfaceExample upperCase = String::toUpperCase;
        System.out.println(upperCase.modify("Little Birds"));

        // Multi Line Lambda
        FnInterfaceExample charCounter = (name -> {
            int charCount = name.length();
            return "'%s' has %d characters.".formatted(name, charCount);
        });
        System.out.println(charCounter.modify("Salome"));

        // Higher Order Function
        HigherOrderFn.modifier(name -> new StringBuilder(name).reverse().toString());
        HigherOrderFn.modifier(upperCase);
        HigherOrderFn.modifier(String::toLowerCase);

        // ----------------------------------------------------------------
        HigherOrderFn.modifier(HigherOrderFn.lengthFinder());

        // ----------------------------------------------------------------
        String len = HigherOrderFn.lengthFinder().modify("Tell Me!!!");
        System.out.println("Length Is: " + len);

        // ----------------------------------------------------------------
        HigherOrderFn.getReplacer().accept("This Is A Sample Text", "\\W");

        // ----------------------------------------------------------------
        Predicate<Integer> gt2 = HigherOrderFn.isGreaterThan(2);
        System.out.println("5 > 2?? --> " + gt2.test(5));

        // ----------------------------------------------------------------
        Predicate<Integer> lt5 = HigherOrderFn.isLessThan(5);
        System.out.println("3 < 5?? --> " + lt5.test(3));

        // ----------------------------------------------------------------
        Predicate<Integer> gt2Andlt5 = gt2.and(lt5);
        System.out.println("2 < 4 < 5?? --> " + gt2Andlt5.test(4));

        // ----------------------------------------------------------------
        System.out.println("--- Integer Summation ----");
        TriConsumer<Integer> triConsumeInt = (x, y, z) -> System.out.println(x + y + z);
        triConsumeInt.consumer(1, 2, 3);

        System.out.println("--- String Summation ----");
        TriConsumer<String> triConsumeStr = (x, y, z) -> System.out.println(x + "-" + y + "-" + z);
        triConsumeStr.consumer("Apple", "Mango", "Tiger");

        System.out.println("--- Double string length ----");
        Function<String, Integer> strLen = String::length;
        Function<Integer, Integer> dbl = i -> i * i;
        Function<Integer, Integer> plus2 = i -> i + 2;

        System.out.println(strLen.andThen(dbl).apply("CamelCase")); // strLen -> dbl
        System.out.println(plus2.compose(dbl).apply(8)); // dbl -> plus2
    }
}
