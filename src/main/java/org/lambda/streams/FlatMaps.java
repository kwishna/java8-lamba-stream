package org.lambda.streams;

import java.util.List;

public class FlatMaps {
    public static void main(String[] args) {
        List<Integer> a = List.of(1, 2, 3, 4, 5);
        List<Integer> b = List.of(6, 7, 8, 9, 10);
        List<Integer> c = List.of(11, 12, 13, 14, 15);

        List<List<Integer>> abc = List.of(a, b, c);

        abc.stream()
                .flatMap(_l -> _l.stream())
                .map(_i -> _i * _i)
                .forEach(System.out::println);
    }
}
