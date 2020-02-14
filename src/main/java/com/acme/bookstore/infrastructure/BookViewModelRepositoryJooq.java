package com.acme.bookstore.infrastructure;

import com.acme.bookstore.domain.BookViewModelRepository;
import com.acme.bookstore.usecase.model.BookViewModel;
import io.vavr.collection.List;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookViewModelRepositoryJooq implements BookViewModelRepository {

    @Autowired
    public BookViewModelRepositoryJooq(DSLContext jooq) {
        this.jooq = jooq;
    }

    @Override
    public List<BookViewModel> findAll() {
        return List.ofAll(jooq.select(List.of(
                BookCatalogRepositoryJooq.ID.as("isbn"),
                BookCatalogRepositoryJooq.TITLE,
                BookCatalogRepositoryJooq.DESCRIPTION,
                AuthorRepositoryJooq.NAME.as("author")).asJava())
                .from(BookCatalogRepositoryJooq.TABLE)
                .join(AuthorRepositoryJooq.TABLE)
                .on(BookCatalogRepositoryJooq.AUTHOR.equal(AuthorRepositoryJooq.ID))
                .fetchInto(BookViewModel.class));
    }

    private final DSLContext jooq;
}
