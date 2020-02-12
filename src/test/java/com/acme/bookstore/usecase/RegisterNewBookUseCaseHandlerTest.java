package com.acme.bookstore.usecase;

import com.acme.bookstore.domain.*;
import com.acme.bookstore.infrastructure.*;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class RegisterNewBookUseCaseHandlerTest {
    @Test
    void ItCanRegisterABook() {
        final BookCatalogRepositoryInMemory catalogRepository = new BookCatalogRepositoryInMemory();
        final AuthorRepositoryInMemory authorRepository = new AuthorRepositoryInMemory();
        final RegisterNewBookUseCase useCase = new RegisterNewBookUseCase("Mon livre", "Arnaud", "Super livre");
        final RegisterNewBookUseCaseHandler handler = new RegisterNewBookUseCaseHandler(catalogRepository, authorRepository);

        final UUID uuid = handler.handle(useCase);

        final Book actualBook = catalogRepository.get(uuid);
        final UUID authorId = actualBook.author();
        final Author actualAuthor = authorRepository.get(authorId);
        assertThat(actualBook.title()).isEqualTo("Mon livre");
        assertThat(actualAuthor.name()).isEqualTo("Arnaud");
        assertThat(actualBook.description()).isEqualTo("Super livre");
    }

    @Test
    void itDoesntCreateTheSameAuthorTwice() {
        final BookCatalogRepositoryInMemory catalogRepository = new BookCatalogRepositoryInMemory();
        final AuthorRepositoryInMemory authorRepository = new AuthorRepositoryInMemory();
        final RegisterNewBookUseCase aUseCase = new RegisterNewBookUseCase("Mon livre", "Arnaud", "Super livre");
        final RegisterNewBookUseCase anotherUseCase = new RegisterNewBookUseCase("Mon autre livre", "Arnaud", "Super livre");
        final RegisterNewBookUseCaseHandler handler = new RegisterNewBookUseCaseHandler(catalogRepository, authorRepository);

        final UUID firstBookId = handler.handle(aUseCase);
        final UUID secondBookId = handler.handle(anotherUseCase);

        final Book firstBook = catalogRepository.get(firstBookId);
        final Book secondBook= catalogRepository.get(secondBookId);
        assertThat(firstBook.author()).isEqualTo(secondBook.author());
    }
}