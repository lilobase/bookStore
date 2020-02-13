package com.acme.bookstore.usecase;

import com.acme.bookstore.common.UseCaseHandler;
import com.acme.bookstore.domain.*;
import io.vavr.collection.List;

public class FindAllBooksUseCaseHandler implements UseCaseHandler<Void, List<Book>> {

    public FindAllBooksUseCaseHandler(BookCatalogRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> handle(Void useCaseParam) {
        return repository.findAll();
    }

    private final BookCatalogRepository repository;
}
