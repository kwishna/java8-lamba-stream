package org.lambda.impl;

import org.lambda.interfaces.Greetings;

public class GreetingMain {
    public static void main(String[] args) {
        // 1st Implementation:
        Greetings hiGreet = new Greetings() {
            @Override
            public void greet(String name) {
                System.out.println("Hi, " + name);
            }

            @Override
            public void bold(String name) {
                System.out.println("HI, " + name.toUpperCase());
            }
        };
        hiGreet.greet("Krishna");
        hiGreet.bold("Krishna");

        // 2nd Implementation:
//        Greetings helloGreet = ((name) -> System.out.println("Hello, " + name)); // java: incompatible types: org.lambda.interfaces.Greetings is not a functional interface
    }
}
