package com.acme.bookstore.usecase;

import com.acme.bookstore.common.UseCaseHandler;
import com.acme.bookstore.domain.*;

import java.util.UUID;

public class RegisterNewBookUseCaseHandler implements UseCaseHandler<RegisterNewBookUseCase, ISBN> {

    public RegisterNewBookUseCaseHandler(BookCatalogRepository catalogRepository, AuthorRepository authorRepository) {
        this.catalogRepository = catalogRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public ISBN handle(RegisterNewBookUseCase useCaseParam) {
        final Author author = authorRepository
                .findByAuthorByName(useCaseParam.author)
                .getOrElse(() -> {
                    final Author newAuthor = new Author(UUID.randomUUID(), useCaseParam.author);
                    authorRepository.add(newAuthor);
                    return newAuthor;
                });
        final Book book = new Book(ISBN.fromString(useCaseParam.isbn), useCaseParam.title, author.id(), useCaseParam.description);
        catalogRepository.add(book);
        return book.id();
    }

    private final BookCatalogRepository catalogRepository;
    private final AuthorRepository authorRepository;
}
