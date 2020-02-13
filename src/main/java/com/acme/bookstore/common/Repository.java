package com.acme.bookstore.common;

import com.acme.bookstore.domain.BookInventoryRecord;
import io.vavr.control.Option;

import java.util.UUID;

public interface Repository<I, T extends Entity<I>> {
    void add(T entity);

    T get(I id);

    Option<T> find(I id);
}
