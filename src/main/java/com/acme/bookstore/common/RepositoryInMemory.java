package com.acme.bookstore.common;

import io.vavr.collection.*;

public abstract class RepositoryInMemory<I, E extends Entity<I>> implements Repository<E> {
    public void add(E entity) {
        entities = entities.put(entity.id(), entity);
    }

    public E get(I id) {
        return entities.get(id).get();
    }

    public Map<I, E> entities() {
        return entities;
    }

    private Map<I, E> entities = HashMap.empty();
}
