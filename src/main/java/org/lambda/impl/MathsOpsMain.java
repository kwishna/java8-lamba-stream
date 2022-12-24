package org.lambda.impl;

import org.lambda.interfaces.MathsOps;

public class MathsOpsMain {
    private static MathsOps add = Math::addExact;
    private static MathsOps sub = Math::subtractExact;
    private static MathsOps mul = Math::multiplyExact;
    private static MathsOps div = Math::floorDiv;

    private static void calculate(MathsOps ops) {
        int a = 15, b = 3;
//        System.out.printf("Operation Between %d And %d --> %d%n", a, b, ops.operate(a, b));
        calculate(a, ops, b);
    }

    private static void calculate(int a, MathsOps ops, int b) {
        System.out.printf("Operation Between %d And %d --> %d%n", a, b, ops.operate(a, b));
    }

    public static void main(String[] args) {
        System.out.println("|-------------------------------------|");
        System.out.println("| add.operate(5, 8) --> " + add.operate(5, 8));
        System.out.println("| sub.operate(53, 16) --> " + sub.operate(53, 16));
        System.out.println("| mul.operate(15, 18) --> " + mul.operate(15, 18));
        System.out.println("| div.operate(26, 3) --> " + div.operate(26, 3));
        System.out.println("|-------------------------------------|");

        System.out.print("| + ");
        calculate(add);
        System.out.print("| - ");
        calculate(sub);
        System.out.print("| * ");
        calculate(mul);
        System.out.print("|  / ");
        calculate(div);
        System.out.println("|-------------------------------------|");

        System.out.print("| + ");
        calculate(5, add, 8);
        System.out.print("| - ");
        calculate(53, sub, 16);
        System.out.print("| * ");
        calculate(15, mul, 18);
        System.out.print("| / ");
        calculate(26, div, 3);
        System.out.println("|-------------------------------------|");
    }
}
