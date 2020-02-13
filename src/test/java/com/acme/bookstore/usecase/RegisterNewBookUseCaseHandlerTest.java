package com.acme.bookstore.usecase;

import com.acme.bookstore.domain.*;
import com.acme.bookstore.infrastructure.*;
import org.junit.jupiter.api.*;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class RegisterNewBookUseCaseHandlerTest {
    @BeforeEach
    void setUp() {
        catalogRepository = new BookCatalogRepositoryInMemory();
        authorRepository = new AuthorRepositoryInMemory();
        handler = new RegisterNewBookUseCaseHandler(catalogRepository, authorRepository);
    }

    @Test
    void ItCanRegisterABook() {
        final RegisterNewBookUseCase useCase = new RegisterNewBookUseCase("Mon livre", "Arnaud", "Super livre", "isbn");

        final ISBN isbn = handler.handle(useCase);

        final Book actualBook = catalogRepository.get(ISBN.fromString("isbn"));
        final UUID authorId = actualBook.author();
        final Author actualAuthor = authorRepository.get(authorId);
        assertThat(actualBook.title()).isEqualTo("Mon livre");
        assertThat(actualAuthor.name()).isEqualTo("Arnaud");
        assertThat(actualBook.description()).isEqualTo("Super livre");
    }

    @Test
    void itDoesntCreateTheSameAuthorTwice() {
        final RegisterNewBookUseCase aUseCase = new RegisterNewBookUseCase("Mon livre", "Arnaud", "Super livre", "isbn");
        final RegisterNewBookUseCase anotherUseCase = new RegisterNewBookUseCase("Mon autre livre", "Arnaud", "Super livre", "isbn");

        final ISBN firstBookId = handler.handle(aUseCase);
        final ISBN secondBookId = handler.handle(anotherUseCase);

        final Book firstBook = catalogRepository.get(firstBookId);
        final Book secondBook= catalogRepository.get(secondBookId);
        assertThat(firstBook.author()).isEqualTo(secondBook.author());
    }

    private BookCatalogRepositoryInMemory catalogRepository;
    private AuthorRepositoryInMemory authorRepository;
    private RegisterNewBookUseCaseHandler handler;
}