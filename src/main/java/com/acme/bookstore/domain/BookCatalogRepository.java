package com.acme.bookstore.domain;

import com.acme.bookstore.common.Repository;

import java.util.UUID;

public interface BookCatalogRepository extends Repository<ISBN, Book> {

}
