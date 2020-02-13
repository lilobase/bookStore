package com.acme.bookstore.domain;

import com.acme.bookstore.common.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Book implements Entity<ISBN> {
    public Book(ISBN id, String title, UUID author, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
    }

    public ISBN id() {
        return id;
    }

    @JsonProperty
    public ISBN isbn() {
        return id();
    }

    @JsonProperty
    public String title() {
        return title;
    }

    @JsonProperty
    public UUID author() {
        return author;
    }

    @JsonProperty
    public String description() {
        return description;
    }

    private ISBN id;
    private String title;
    private UUID author;
    private String description;
}
