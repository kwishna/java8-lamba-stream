package org.lambda;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        /*
        Lambda Intermediate Operations
        Lambda Terminal Operations
         */
//        Runnable runnable = () -> {
//            Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
//            System.out.println("Hello World!!");
//        };
//        new Thread(runnable).start();
//
//        Supplier<Double> random1 = Math::random;
//        Callable<Double> random2 = Math::random;
//
//        System.out.println(random1.get());
//        System.out.println(random2.call());


        Map<Integer, String> mapping = new HashMap<>();

        mapping.put(1, "z");
        mapping.put(6, "k");
        mapping.put(5, "a");
        mapping.put(3, "f");
        mapping.put(9, "c");

        Map<Integer, String> sortedMap =
                mapping.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(
                                Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new)
                        );
        System.out.println("Sorted Map: " + Arrays.toString(sortedMap.entrySet().toArray()));
    }
}