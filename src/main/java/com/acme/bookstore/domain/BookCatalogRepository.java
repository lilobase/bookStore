package com.acme.bookstore.domain;

import com.acme.bookstore.common.Repository;
import io.vavr.collection.List;

public interface BookCatalogRepository extends Repository<ISBN, Book> {

    List<Book> findAll();
}
