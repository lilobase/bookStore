package com.acme.bookstore.usecase;

import com.acme.bookstore.domain.*;
import com.acme.bookstore.infrastructure.*;
import com.acme.bookstore.infrastructure.readmodel.BookViewModelDaoJooq;
import com.acme.bookstore.usecase.model.BookViewModel;
import infrastructure.InMemoryDSLContext;
import io.vavr.collection.List;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class FindAllBooksUseCaseHandlerTest {
    @Test
    void itReturnsAllBooks() {
        final DSLContext jooq = InMemoryDSLContext.DSL();
        initBookAndAuthor(jooq);
        final BookViewModelRepository repository = new BookViewModelDaoJooq(jooq);
        final FindAllBooksUseCaseHandler handler = new FindAllBooksUseCaseHandler(repository);

        final List<BookViewModel> books = handler.handle(null);

        assertThat(books).hasSize(1);
        assertThat(books.head().author).isEqualTo("Arnaud");
    }

    private void initBookAndAuthor(DSLContext jooq) {
        final BookCatalogRepositoryJooq bookRepository = new BookCatalogRepositoryJooq(jooq);
        final AuthorRepositoryJooq authorRepository = new AuthorRepositoryJooq(jooq);
        final UUID authorId = UUID.randomUUID();
        authorRepository.add(new Author(authorId, "Arnaud"));
        bookRepository.add(new Book(ISBN.fromString("isbn"), "title", authorId, "description"));
    }
}