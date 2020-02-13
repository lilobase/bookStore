package com.acme.bookstore.infrastructure;

import com.acme.bookstore.domain.*;
import io.vavr.collection.*;
import io.vavr.control.Option;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BookCatalogRepositoryJooq implements BookCatalogRepository {

    @Autowired
    public BookCatalogRepositoryJooq(DSLContext jooq) {
        this.jooq = jooq;
    }

    @Override
    public List<Book> findAll() {
        return List.ofAll(
                jooq
                        .select(ALL_FIELDS.asJava())
                        .from(TABLE)
                        .fetch()
                        .map(this::map)
        );
    }

    @Override
    public void add(Book entity) {
        jooq
                .insertInto(TABLE)
                .set(HashMap.of(
                        ID, entity.id(),
                        TITLE, entity.title(),
                        AUTHOR, entity.author(),
                        DESCRIPTION, entity.description()
                ).toJavaMap())
                .execute();
    }

    @Override
    public Book get(ISBN id) {
        return find(id).get();
    }

    @Override
    public Option<Book> find(ISBN id) {
        return Option.ofOptional(
                jooq
                        .select(ALL_FIELDS.asJava())
                        .from(TABLE)
                        .where(ID.equal(id.toString()))
                        .fetchOptional(this::map)
        );
    }

    private Book map(Record record) {
        return new Book(
                ISBN.fromString(ID.get(record)),
                TITLE.get(record),
                AUTHOR.get(record),
                DESCRIPTION.get(record)
        );
    }

    public static final Field<String> ID = DSL.field("isbn", String.class);
    public static final Field<String> TITLE = DSL.field("title", String.class);
    public static final Field<UUID> AUTHOR = DSL.field("author", UUID.class);
    public static final Field<String> DESCRIPTION = DSL.field("description", String.class);
    public static final Table<Record> TABLE = DSL.table("catalog");
    public static final List<Field<?>> ALL_FIELDS = List.of(ID, TITLE, AUTHOR, DESCRIPTION);
    private final DSLContext jooq;
}
