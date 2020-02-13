package com.acme.bookstore.infrastructure;

import com.acme.bookstore.common.RepositoryInMemory;
import com.acme.bookstore.domain.*;
import io.vavr.collection.List;

public class BookCatalogRepositoryInMemory extends RepositoryInMemory<ISBN, Book> implements BookCatalogRepository {
    @Override
    public List<Book> findAll() {
        return List.ofAll(entities().values());
    }
}
