package com.acme.bookstore.domain;

import com.acme.bookstore.common.Entity;
import com.acme.bookstore.usecase.PositiveInteger;

import java.util.UUID;

public class BookInventoryRecord implements Entity<UUID> {
    public BookInventoryRecord(UUID bookId, PositiveInteger quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }

    @Override
    public UUID id() {
        return bookId;
    }

    public PositiveInteger quantity() {
        return quantity;
    }

    private final UUID bookId;
    private final PositiveInteger quantity;
}
