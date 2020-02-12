package com.acme.bookstore.common;

import com.acme.bookstore.domain.Book;

import java.util.UUID;

public interface Repository<T extends AggregateRoot> {
    void add(T entity);

    T get(UUID id);
}
