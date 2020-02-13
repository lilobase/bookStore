package com.acme.bookstore.common;

import io.vavr.collection.*;
import io.vavr.control.Option;

public abstract class RepositoryInMemory<I, E extends Entity<I>> implements Repository<I, E> {
    public void add(E entity) {
        entities = entities.put(entity.id(), entity);
    }

    public E get(I id) {
        return find(id).get();
    }

    public Option<E> find(I id) {
        return entities.get(id);
    }

    public Map<I, E> entities() {
        return entities;
    }

    private Map<I, E> entities = HashMap.empty();
}
