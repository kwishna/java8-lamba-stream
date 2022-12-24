package org.lambda.streams;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class IsEvenAndDouble {
    // Check Even Number.
    // Interested In Only 3 Items.
    // Find The Square.
    // Print It On Console.
    public static void main(String[] args) {
        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21);

        Predicate<Integer> IS_EVEN = num -> num % 2 == 0;
        Function<Integer, Integer> DOUBLE = num -> 2 * num;

        nums
                .stream()
                .filter(IS_EVEN)
                .peek(_n -> System.out.println("?? EVEN --> " + _n))
                .limit(5)
                .map(DOUBLE)
                .peek(_n -> System.out.println("__ DOUBLE --> " + _n))
                .skip(2)
                .forEach(_n -> System.err.println("## FINAL --> " + _n));
    }
}
