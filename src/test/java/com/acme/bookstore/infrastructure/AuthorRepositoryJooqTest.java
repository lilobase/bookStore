package com.acme.bookstore.infrastructure;

import com.acme.bookstore.domain.Author;
import infrastructure.InMemoryDSLContext;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class AuthorRepositoryJooqTest {
    @Test
    void itPersists() {
        final AuthorRepositoryJooq repository = new AuthorRepositoryJooq(InMemoryDSLContext.DSL());

        final Author expectedAuthor = new Author(UUID.randomUUID(), "Arnaud");
        repository.add(expectedAuthor);

        final Author actualAuthor = repository.get(expectedAuthor.id());
        assertThat(actualAuthor).isEqualToComparingFieldByField(expectedAuthor);
    }
}