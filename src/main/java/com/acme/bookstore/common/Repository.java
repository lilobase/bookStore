package com.acme.bookstore.common;

import java.util.UUID;

public interface Repository<T extends Entity> {
    void add(T entity);

    T get(UUID id);
}
