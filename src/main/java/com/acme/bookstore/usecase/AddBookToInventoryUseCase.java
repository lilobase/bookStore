package com.acme.bookstore.usecase;

import com.acme.bookstore.common.UseCase;
import com.acme.bookstore.domain.*;

public class AddBookToInventoryUseCase implements UseCase {
    public AddBookToInventoryUseCase(ISBN bookId, PositiveInteger quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public final ISBN bookId;
    public final PositiveInteger quantity;
}
