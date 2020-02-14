package com.acme.bookstore.usecase;

import com.acme.bookstore.common.UseCaseHandler;
import com.acme.bookstore.domain.*;
import com.acme.bookstore.usecase.model.BookViewModel;
import io.vavr.collection.List;

public class FindAllBooksUseCaseHandler implements UseCaseHandler<FindAllBooksUseCase, List<BookViewModel>> {

    public FindAllBooksUseCaseHandler(BookViewModelRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<BookViewModel> handle(FindAllBooksUseCase useCaseParam) {
        return repository.findAll();
    }

    private final BookViewModelRepository repository;
}
