package com.acme.bookstore.usecase;

import com.acme.bookstore.domain.*;
import com.acme.bookstore.infrastructure.InventoryRepositoryInMemory;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

class AddBookToInventoryUseCaseHandlerTest {
    @BeforeEach
    void setUp() {
        bookId = ISBN.fromString("isbn");
        repository = new InventoryRepositoryInMemory();
        handler = new AddBookToInventoryUseCaseHandler(repository);
    }

    @Test
    void ItAddABookInventoryRecordToTheInventory() {
        final AddBookToInventoryUseCase useCase = new AddBookToInventoryUseCase(bookId, new PositiveInteger(5));

        handler.handle(useCase);

        final BookInventoryRecord actualRecord = repository.get(bookId);
        assertThat(actualRecord.quantity()).isEqualTo(new PositiveInteger(5));
    }

    @Test
    void ItAddQuantityRelativelyToThePreviousOne() {
        final AddBookToInventoryUseCase useCase1 = new AddBookToInventoryUseCase(bookId, new PositiveInteger(5));
        final AddBookToInventoryUseCase useCase2 = new AddBookToInventoryUseCase(bookId, new PositiveInteger(3));

        handler.handle(useCase1);
        handler.handle(useCase2);

        final BookInventoryRecord actualRecord = repository.get(bookId);
        assertThat(actualRecord.quantity()).isEqualTo(new PositiveInteger(8));
    }

    private ISBN bookId;
    private InventoryRepository repository;
    private AddBookToInventoryUseCaseHandler handler;
}