package com.acme.bookstore.infrastructure;

import com.acme.bookstore.common.RepositoryInMemory;
import com.acme.bookstore.domain.*;

public class BookCatalogRepositoryInMemory extends RepositoryInMemory<ISBN, Book> implements BookCatalogRepository {
}
