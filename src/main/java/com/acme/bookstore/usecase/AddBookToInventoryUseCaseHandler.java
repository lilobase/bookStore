package com.acme.bookstore.usecase;

import com.acme.bookstore.common.UseCaseHandler;
import com.acme.bookstore.domain.*;

public class AddBookToInventoryUseCaseHandler implements UseCaseHandler<AddBookToInventoryUseCase, Void> {
    public AddBookToInventoryUseCaseHandler(InventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Void handle(AddBookToInventoryUseCase useCaseParam) {
        repository.add(getOrCreate(useCaseParam));
        return null;
    }

    private BookInventoryRecord getOrCreate(AddBookToInventoryUseCase useCaseParam) {
        try {
            final BookInventoryRecord record = repository.get(useCaseParam.bookId);
            return record.addQuantity(useCaseParam.quantity);
        } catch (Exception e) {
            return new BookInventoryRecord(useCaseParam.bookId, useCaseParam.quantity);
        }
    }

    private final InventoryRepository repository;
}
