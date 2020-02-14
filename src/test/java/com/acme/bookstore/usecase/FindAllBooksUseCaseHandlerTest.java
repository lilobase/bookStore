package com.acme.bookstore.usecase;

import com.acme.bookstore.domain.*;
import com.acme.bookstore.infrastructure.BookCatalogRepositoryInMemory;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class FindAllBooksUseCaseHandlerTest {
    @Test
    void itReturnsAllBooks() {
        final BookCatalogRepository repository = new BookCatalogRepositoryInMemory();
        repository.add(new Book(ISBN.fromString("isbn"), "title", UUID.randomUUID(), "desc"));
        repository.add(new Book(ISBN.fromString("autre_isbn"), "title", UUID.randomUUID(), "desc"));
        final FindAllBooksUseCaseHandler handler = new FindAllBooksUseCaseHandler(repository);


        final List<Book> books = handler.handle(null);

        assertThat(books).hasSize(2);
    }
}