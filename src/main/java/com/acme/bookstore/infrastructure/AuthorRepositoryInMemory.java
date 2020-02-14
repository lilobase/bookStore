package com.acme.bookstore.infrastructure;

import com.acme.bookstore.common.RepositoryInMemory;
import com.acme.bookstore.domain.*;
import io.vavr.Tuple2;
import io.vavr.control.Option;

import java.util.UUID;

public class AuthorRepositoryInMemory extends RepositoryInMemory<UUID, Author> implements AuthorRepository {
    @Override
    public Option<Author> findAuthorByName(String name) {
        return entities()
                .find(e -> e._2().name().equals(name))
                .map(Tuple2::_2);
    }
}
