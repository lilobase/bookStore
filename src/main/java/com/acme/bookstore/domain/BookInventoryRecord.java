package com.acme.bookstore.domain;

import com.acme.bookstore.common.Entity;

public class BookInventoryRecord implements Entity<ISBN> {
    public BookInventoryRecord(ISBN bookId, PositiveInteger quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public static BookInventoryRecord nullObject(ISBN bookId) {
        return new Blank(bookId);
    }

    @Override
    public ISBN id() {
        return bookId;
    }

    public PositiveInteger quantity() {
        return quantity;
    }

    public BookInventoryRecord addQuantity(PositiveInteger quantityToAdd) {
        quantity = quantity.add(quantityToAdd);
        return this;
    }

    private final ISBN bookId;
    private PositiveInteger quantity;

    public static class Blank extends BookInventoryRecord {
        Blank(ISBN bookId) {
            super(bookId, new PositiveInteger(0));
        }
    }
}
