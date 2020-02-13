package com.acme.bookstore.usecase;

import com.acme.bookstore.domain.*;
import com.acme.bookstore.infrastructure.InventoryRepositoryInMemory;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class AddBookToInventoryUseCaseHandlerTest {
    @Test
    void ItAddABookInventoryRecordToTheInventory() {
        final UUID bookId = UUID.randomUUID();
        final AddBookToInventoryUseCase useCase = new AddBookToInventoryUseCase(bookId, new PositiveInteger(5));
        final InventoryRepository repository = new InventoryRepositoryInMemory();
        final AddBookToInventoryUseCaseHandler handler = new AddBookToInventoryUseCaseHandler(repository);

        handler.handle(useCase);

        final BookInventoryRecord actualRecord = repository.get(bookId);
        assertThat(actualRecord.quantity()).isEqualTo(new PositiveInteger(5));
    }
}