package com.acme.bookstore.web.BooksResource;

import com.acme.bookstore.domain.*;
import com.acme.bookstore.usecase.*;
import com.acme.bookstore.usecase.model.BookViewModel;
import io.vavr.collection.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/books")
public class BooksResource {

    @Autowired
    public BooksResource(BookCatalogRepository catalogRepository, AuthorRepository authorRepository, BookViewModelRepository bookViewModelRepository) {
        this.catalogRepository = catalogRepository;
        this.authorRepository = authorRepository;
        this.bookViewModelRepository = bookViewModelRepository;
    }

    @PostMapping
    public String registerBook(@RequestBody RegisterBookUseCase useCase) {
        final RegisterBookUseCaseHandler handler = new RegisterBookUseCaseHandler(
                catalogRepository,
                authorRepository
        );
        return handler.handle(useCase).toString();
    }

    @GetMapping
    public List<BookViewModel> findAll() {
        final FindAllBooksUseCaseHandler handler = new FindAllBooksUseCaseHandler(bookViewModelRepository);
        return handler.handle(new FindAllBooksUseCase());
    }

    private final BookCatalogRepository catalogRepository;
    private final AuthorRepository authorRepository;
    private final BookViewModelRepository bookViewModelRepository;
}
