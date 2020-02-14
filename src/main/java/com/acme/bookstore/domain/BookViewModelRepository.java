package com.acme.bookstore.domain;

import com.acme.bookstore.usecase.model.BookViewModel;
import io.vavr.collection.List;

public interface BookViewModelRepository {
    List<BookViewModel> findAll();
}
