package com.acme.bookstore.usecase;

import com.acme.bookstore.common.UseCase;

import java.util.UUID;

public class AddBookToInventoryUseCase implements UseCase {
    public AddBookToInventoryUseCase(UUID bookId, PositiveInteger quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public final UUID bookId;
    public final PositiveInteger quantity;
}
