package org.lambda.moretests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

// https://vins-udemy.s3.amazonaws.com/java/html/drop-down.html
// https://vins-udemy.s3.amazonaws.com/java/html/java8-payment-screen.html
public class AssignmentTests {

    @Test
    public void test1() throws IOException {
        Path path = Path.of("./src/test/resources/first-names.txt");
        List<String> listStr = Files.readAllLines(path);
        long _count = listStr.stream().filter(_l -> _l.startsWith("B")).count();
        System.out.println("Lines Count --> " + _count);
        Assert.assertEquals(_count, 257);

        List<String> _cs = listStr.stream()
                .filter(_l -> _l.startsWith("C"))
                .filter(_l -> _l.contains("s"))
                .toList();
        System.out.println("Lines Count filter 'cS' --> " + _cs.size());
        Assert.assertEquals(_cs.size(), 113);

        int charSum = listStr.stream()
                .filter(_name -> _name.startsWith("M"))
                .map(String::trim)
                .map(String::length)
                .mapToInt(i -> i)
                .sum();
        Assert.assertEquals(charSum, 3054);
        System.out.println("Total characters sum starting with 'M' --> " + charSum);

        List<String> _replace = listStr.stream()
                .filter(_name -> _name.contains("-"))
                .map(_l -> _l.replace("-", " "))
                .toList();
        Assert.assertEquals(_replace.size(), 19);
        System.out.println("Total Replaced names --> " + _replace.size());

        Optional<String> opStr = listStr.stream()
                .filter(_name -> _name.contains("-"))
                .max(Comparator.comparing(String::length));
        System.out.println("Longest name --> " + opStr.get());
        Assert.assertEquals(opStr.get(), "Helen-Elizabeth");
    }
}
