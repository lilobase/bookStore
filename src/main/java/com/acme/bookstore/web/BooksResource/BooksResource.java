package com.acme.bookstore.web.BooksResource;

import com.acme.bookstore.domain.*;
import com.acme.bookstore.infrastructure.*;
import com.acme.bookstore.usecase.*;
import io.vavr.collection.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/books")
public class BooksResource {

    @Autowired
    public BooksResource(BookCatalogRepository catalogRepository, AuthorRepository authorRepository) {
        this.catalogRepository = catalogRepository;
        this.authorRepository = authorRepository;
    }

    @PostMapping
    public String registerBook(@RequestBody RegisterNewBookUseCase useCase) {
        final RegisterNewBookUseCaseHandler handler = new RegisterNewBookUseCaseHandler(
                catalogRepository,
                authorRepository
        );
        return handler.handle(useCase).toString();
    }

    @GetMapping
    public List<String> findAll() {
        final FindAllBooksUseCaseHandler handler = new FindAllBooksUseCaseHandler(catalogRepository);
        return handler.handle(null).map(e -> e.title());
    }

    private final BookCatalogRepository catalogRepository;
    private final AuthorRepository authorRepository;
}
