package org.lambda.impl;

import com.google.common.util.concurrent.Uninterruptibles;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class CallableRunnable {

    private static Double test(Callable<Double> callable) throws Exception {
        return callable.call();
    }

    public static void main(String[] args) throws Exception {
        Runnable runnable = () -> {
            Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
            System.out.println("Hello World!!");
        };
        new Thread(runnable).start();

        Supplier<Double> random1 = Math::random;
        System.out.println(random1.get());

        Callable<Double> random2 = Math::random;
        System.out.println(random2.call());
        System.out.println(test(random2));
    }
}
