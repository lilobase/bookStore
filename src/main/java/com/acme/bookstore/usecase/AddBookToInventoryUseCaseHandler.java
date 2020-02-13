package com.acme.bookstore.usecase;

import com.acme.bookstore.common.*;
import com.acme.bookstore.domain.*;

public class AddBookToInventoryUseCaseHandler implements UseCaseHandler<AddBookToInventoryUseCase, Void> {
    public AddBookToInventoryUseCaseHandler(InventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Void handle(AddBookToInventoryUseCase useCaseParam) {
        final BookInventoryRecord record = new BookInventoryRecord(useCaseParam.bookId, useCaseParam.quantity);
        repository.add(record);
        return null;
    }

    private final InventoryRepository repository;
}
