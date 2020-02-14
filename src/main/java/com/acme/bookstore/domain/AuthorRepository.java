package com.acme.bookstore.domain;

import com.acme.bookstore.common.Repository;
import io.vavr.control.Option;

import java.util.UUID;

public interface AuthorRepository extends Repository<UUID, Author> {
    Option<Author> findAuthorByName(String name);
}
