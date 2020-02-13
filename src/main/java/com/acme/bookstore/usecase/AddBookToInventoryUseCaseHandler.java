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
        final BookInventoryRecord record = repository
                .find(useCaseParam.bookId)
                .getOrElse(() -> BookInventoryRecord.nullObject(useCaseParam.bookId));
        record.addQuantity(useCaseParam.quantity);
        return record;
    }

    private final InventoryRepository repository;
}
