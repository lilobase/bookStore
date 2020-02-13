package com.acme.bookstore.infrastructure;

import com.acme.bookstore.common.RepositoryInMemory;
import com.acme.bookstore.domain.*;
import io.vavr.Tuple2;
import io.vavr.control.Option;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthorRepositoryInMemory extends RepositoryInMemory<UUID, Author> implements AuthorRepository {
    @Override
    public Option<Author> findByAuthorByName(String name) {
        return entities()
                .find(e -> e._2().name().equals(name))
                .map(Tuple2::_2);
    }
}
