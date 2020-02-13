package com.acme.bookstore.usecase;

import java.util.Objects;

public class PositiveInteger {
    public PositiveInteger(int number) {
        this.number = number;
        if(number < 0) throw new IllegalArgumentException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositiveInteger that = (PositiveInteger) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "PositiveInteger{" +
                "number=" + number +
                '}';
    }

    public PositiveInteger add(PositiveInteger quantity) {
        return new PositiveInteger(quantity.number + number);
    }

    public final int number;
}
