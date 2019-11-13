package ru.spbstu.icst.collection;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * Array-based LIFO container
 */
public class Stack implements Iterable<Double> {

    private double[] storage;
    private int top;

    public Stack() {
        this(10);
    }

    public Stack(int initialSize) {
        if (initialSize < 0) {
            throw new IllegalArgumentException("Stack size must be greater or equal to zero");
        }
        storage = new double[initialSize];
    }

    public void add(double d) {
        if (top == storage.length) {
            double[] newStorage = new double[storage.length * 2 + 1];
            System.arraycopy(storage, 0, newStorage, 0, storage.length);
            storage = newStorage;
        }

        storage[top++] = d;
    }

    public double pop() {
        if (empty()) {
            throw new EmptyStackException();
        }
        return storage[--top];
    }

    public double peek() {
        if (empty()) {
            throw new EmptyStackException();
        }
        return storage[top - 1];
    }

    public boolean empty() {
        return top == 0;
    }

    @Override
    public String toString() {
        return String.format("Stack with %d element(s)", top);
    }

    @Override
    public Iterator<Double> iterator() {
        return new Iterator<Double>() {
            private int index;

            @Override
            public boolean hasNext() {
                return index < storage.length;
            }

            @Override
            public Double next() {
                return storage[index++];
            }
        };
    }

}
