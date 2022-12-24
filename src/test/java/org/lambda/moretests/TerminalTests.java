package org.lambda.moretests;

import org.apache.commons.lang3.RandomUtils;
import org.lambda.pojo.Student;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TerminalTests {

    private static Optional<String> getSmallest(String... args) {
        return Stream.of(args).min(Comparator.naturalOrder());
    }

    //    @Test(expectedExceptions = IllegalStateException.class)
    @Test
    public void illegalStateException() {
        List<String> fruits = List.of("Apple", "Mango", "Grapes", "Oranges", "Watermelon", "Kiwi", "Bananas", "PineApple");
        Stream<String> fruitStream = fruits.stream().filter(str -> str.length() > 5);

        fruitStream.forEach(System.out::println);
        fruitStream.forEach(System.out::println);
    }

    @Test
    public void nullable() {
//        Stream<String> fruitStream = Stream.of("Apple", "Mango", "Grapes", "Oranges", "Watermelon", "Kiwi", "Bananas", "PineApple");
        Optional<String> small = TerminalTests.getSmallest("Apple", "Mango", "Grapes", "Oranges", "Watermelon", "Kiwi", "Bananas", "PineApple");
        small.ifPresent(s -> System.out.println("Smallest --> " + s));

        System.out.println(Optional.ofNullable(null));
    }

    @Test
    public void comparator() {
        System.out.println("min natural order --> " + Stream.of("Apple", "Mango", "Grapes", "Oranges", "Watermelon", "Kiwi", "Bananas", "PineApple").min(Comparator.naturalOrder()));
        System.out.println("max natural order --> " + Stream.of("Apple", "Mango", "Grapes", "Oranges", "Watermelon", "Kiwi", "Bananas", "PineApple").max(Comparator.naturalOrder()));
        System.err.println("------ Sorted natural order -------");
        Stream.of("Apple", "Mango", "Grapes", "Oranges", "Watermelon", "Kiwi", "Bananas", "PineApple").sorted(Comparator.naturalOrder()).forEach(_l -> System.out.printf("%s, ", _l));

        System.out.println();
        System.out.println("min reverse order --> " + Stream.of("Apple", "Mango", "Grapes", "Oranges", "Watermelon", "Kiwi", "Bananas", "PineApple").min(Comparator.reverseOrder()));
        System.out.println("max reverse order --> " + Stream.of("Apple", "Mango", "Grapes", "Oranges", "Watermelon", "Kiwi", "Bananas", "PineApple").max(Comparator.reverseOrder()));
        System.err.println("-------- Sorted reverse order -------");
        Stream.of("Apple", "Mango", "Grapes", "Oranges", "Watermelon", "Kiwi", "Bananas", "PineApple").sorted(Comparator.reverseOrder()).forEach(_l -> System.out.printf("%s, ", _l));

        System.out.println();
        System.out.println("min comparing order --> " + Stream.of("Apple", "Mango", "Grapes", "Oranges", "Watermelon", "Kiwi", "Bananas", "PineApple").min(Comparator.comparing(String::length)));
        System.out.println("max comparing order --> " + Stream.of("Apple", "Mango", "Grapes", "Oranges", "Watermelon", "Kiwi", "Bananas", "PineApple").max(Comparator.comparing(String::length)));
        System.err.println("-------- Sorted length order -------");
        Stream.of("Apple", "Mango", "Grapes", "Oranges", "Watermelon", "Kiwi", "Bananas", "PineApple").sorted(Comparator.comparing(String::length)).forEach(_l -> System.out.printf("%s, ", _l));
    }

    @Test
    public void comparing() {
        Student s1 = new Student() {{
            setName("Ganesh");
            setRollNo(RandomUtils.nextInt(0, 10));
            setSection('A');
        }};

        Student s2 = new Student() {{
            setName("Ramesh");
            setRollNo(RandomUtils.nextInt(0, 10));
            setSection('B');
        }};

        Student s3 = new Student() {{
            setName("Suresh");
            setRollNo(RandomUtils.nextInt(0, 10));
            setSection('C');
        }};

        Optional<Student> minByRole = Stream.of(s1, s2, s3).min(Comparator.comparing(Student::getRollNo));
        Optional<Student> maxByName = Stream.of(s1, s2, s3).max(Comparator.comparing(Student::getName));

        System.out.println(minByRole);
        System.out.println(maxByName);

        Assert.assertEquals(maxByName, Optional.of(s3));
    }

    @Test
    public void reduce() {
        List<Integer> intList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sum = 0;
        for (int i = 0; i <= intList.size(); i++) {
            sum = sum + i;
        }
        Assert.assertEquals(sum, 55, "Sum Mismatch");

        Optional<Integer> _sum1 = intList.stream().reduce(Integer::sum);
        Assert.assertEquals(_sum1.get(), 55, "Sum1 Mismatch");

        Optional<Integer> _sum2 = intList.stream().reduce(0, (a, b) -> a + b).describeConstable();
        Assert.assertEquals(_sum1.get(), 55, "Sum2 Mismatch");

        Integer _sum3 = intList.stream().reduce(0, Integer::sum);
        Assert.assertEquals(_sum3, 55, "Sum3 Mismatch");
    }

    @Test
    public void sum() {
        List<Integer> intList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // List<Integer> to 'Stream<Integer>'.
        Stream<Integer> integerStream = intList.stream();

        // Stream<Integer> to 'List<Integer>'.
        List<Integer> intLi = intList.stream().toList();

        // Stream<Integer> to 'IntStream'.
        IntStream intStream = integerStream.mapToInt(a -> a);

        // 'IntStream' to 'Stream<Integer>'.
        Stream<Integer> iStrm = intList
                .stream()
                .mapToInt(a -> a)
                .boxed();

        Assert.assertEquals(intStream.sum(), 55, "Sum Mismatch");
        Assert.assertEquals(iStrm.mapToInt(a -> a).sum(), 55, "Sum Mismatch");
    }

    @Test
    public void rangeClosed() {
        IntStream integerStream = IntStream.rangeClosed(1, 10);
        integerStream.forEach(a -> System.out.printf("%d, ", a));
    }

    @Test
    public void takeWhile() {
        Stream<Integer> intStream = Stream.of(1, 4, 2, 5, 6, 0, 1, 3, 2, 1, 5, 6, 7, 6, 8, 9);
        // Collect All The Elements Until The Given Condition Is Satisfied.
        intStream.takeWhile(i -> i < 6).forEach(_l -> System.out.println("Less Than 6 --> " + _l));
    }

    @Test
    public void dropWhile() {
        Stream<Integer> intStream = Stream.of(4, 4, 4, 5, 6, 7, 4, 8, 9, 10);
        // Drop All The Elements Until Given Condition Is Satisfied.
        intStream.dropWhile(i -> i == 4).forEach(_l -> System.out.println(" --> " + _l));
    }
}
