package org.lambda.interfaces;

import java.util.function.Consumer;

public interface ICustomList {
    void add(int item);

    int size();

    int get(int index);

    default void forEach(Consumer<Integer> consumer) {
        for (int i = 0; i < size(); i++) {
            consumer.accept(get(i));
        }
    }
}
