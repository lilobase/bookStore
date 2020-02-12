package com.acme.bookstore.domain;

import com.acme.bookstore.common.*;

import java.util.UUID;

public class Author implements EntityWithUUID {
    public Author(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public UUID id() {
        return id;
    }

    public String name() {
        return name;
    }

    private UUID id;
    private String name;
}
