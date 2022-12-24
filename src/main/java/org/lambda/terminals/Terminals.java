package org.lambda.terminals;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Terminals {
    public static void main(String[] args) {
        List<String> fruits = List.of("Apple", "Mango", "Grapes", "Oranges", "Watermelon", "Kiwi", "Bananas", "PineApple");

        System.out.println("findFirst --> " + fruits.stream().filter(str -> str.length() == 5).findFirst());
        System.out.println("findAny --> " + fruits.stream().filter(str -> str.length() > 5).findAny());

        System.out.println("Min --> " + fruits.stream().min(Comparator.naturalOrder()));
        System.out.println("Max --> " + fruits.stream().max(Comparator.naturalOrder()));

        System.out.println("Rev Min --> " + fruits.stream().min(Comparator.reverseOrder()));
        System.out.println("Rev Max --> " + fruits.stream().max(Comparator.reverseOrder()));

        System.out.println("anyMatch --> " + fruits.stream().filter(str -> str.length() == 5).anyMatch(str -> str.contains("o")));
        System.out.println("noneMatch --> " + fruits.stream().filter(str -> str.length() == 9).noneMatch(str -> str.contains("e")));

        String _fruits = fruits.stream().filter(str -> str.length() > 7).collect(Collectors.joining());
        System.out.println("Collected fruits --> " + _fruits);
        assert "WatermelonPineApple".equals(_fruits);

        _fruits = fruits.stream().filter(str -> str.length() > 7).collect(Collectors.joining(":"));
        System.out.println("Collected fruits --> " + _fruits);
        assert "Watermelon:PineApple".equals(_fruits);

        Map<Integer, List<String>> map = fruits.stream().filter(str -> str.length() > 1).collect(Collectors.groupingBy(String::length));
        System.out.println(map);

    }
}
