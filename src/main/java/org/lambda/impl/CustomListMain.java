package org.lambda.impl;

import org.lambda.interfaces.ICustomList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

class CustomList implements ICustomList {
    List<Integer> list = new ArrayList<Integer>();

    /**
     * @param item
     */
    @Override
    public void add(int item) {
        list.add(item);
    }

    /**
     * @return
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * @param index
     * @return
     */
    @Override
    public int get(int index) {
        return list.get(index);
    }

    /**
     * @param consumer
     */
    @Override
    public void forEach(Consumer<Integer> consumer) {
        ICustomList.super.forEach(consumer);
    }
}

public class CustomListMain {
    public static void main(String[] args) {
        CustomList list = new CustomList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.forEach(System.out::println);
    }
}
