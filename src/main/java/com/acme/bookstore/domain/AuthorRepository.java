package com.acme.bookstore.domain;

import com.acme.bookstore.common.Repository;
import io.vavr.control.Option;

public interface AuthorRepository extends Repository<Author> {
    Option<Author> findByAuthorByName(String name);
}
