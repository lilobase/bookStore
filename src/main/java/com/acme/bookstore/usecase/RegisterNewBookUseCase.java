package com.acme.bookstore.usecase;

import com.acme.bookstore.common.UseCase;

public class RegisterNewBookUseCase implements UseCase {

    public RegisterNewBookUseCase(String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
    }

    String title;
    String author;
    String description;
}
