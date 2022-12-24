package org.lambda.interfaces;

@FunctionalInterface
public interface TriConsumer<T> {
    void consumer(T a, T b, T c);
}
