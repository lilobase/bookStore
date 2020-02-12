package com.acme.bookstore.domain;

import com.acme.bookstore.common.*;

import java.util.UUID;

public class Book implements AggregateRootWithUUID {
    public Book(UUID id, String title, UUID author, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
    }

    public UUID id() {
        return id;
    }

    public String title() {
        return title;
    }

    public UUID author() {
        return author;
    }

    public String description() {
        return description;
    }

    private UUID id;
    private String title;
    private UUID author;
    private String description;
}
