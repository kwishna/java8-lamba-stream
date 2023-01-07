package org.lambda.streams;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class FlatMaps {
    public static void main(String[] args) {
        List<Integer> a = List.of(1, 2, 3, 4, 5);
        List<Integer> b = List.of(6, 7, 8, 9, 10);
        List<Integer> c = List.of(11, 12, 13, 14, 15);

        List<List<Integer>> abc = List.of(a, b, c);

        Function<Integer, Integer> SQUARED = num -> num * num;
        Function<List<Integer>, Stream<Integer>> STREAM = Collection::stream;
        Consumer<Integer> PRINT = System.out::println;

        abc.stream()
                .flatMap(STREAM)
                .map(SQUARED)
                .forEach(PRINT);
    }
}
