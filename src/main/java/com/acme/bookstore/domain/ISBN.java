package com.acme.bookstore.domain;

import java.util.Objects;

public class ISBN {
    public ISBN(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ISBN isbn1 = (ISBN) o;
        return Objects.equals(isbn, isbn1.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    public static ISBN fromString(String isbn) {
        return new ISBN(isbn);
    }

    public String toString() {
        return isbn;
    }

    private final String isbn;
}
