package com.acme.bookstore.infrastructure;

import com.acme.bookstore.domain.*;
import io.vavr.collection.*;
import io.vavr.control.Option;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.UUID;

@Component
public class AuthorRepositoryJooq implements AuthorRepository {

    @Autowired
    public AuthorRepositoryJooq(DSLContext jooq) {
        this.jooq = jooq;
    }

    @Override
    public Option<Author> findAuthorByName(String name) {
        return findBy(NAME.equal(name));
    }

    @Override
    public void add(Author entity) {
        jooq.insertInto(TABLE).set(
                HashMap.of(
                        ID, entity.id(),
                        NAME, entity.name()
                ).toJavaMap()
        ).execute();
    }

    @Override
    public Author get(UUID id) {
        return find(id).get();
    }

    @Override
    public Option<Author> find(UUID id) {
        return findBy(ID.equal(id));
    }

    private Option<Author> findBy(Condition condition) {
        return Option.ofOptional(jooq
                .select(ALL_FIELDS.asJava())
                .from(TABLE)
                .where(condition)
                .fetchOptional(this::map));
    }

    private Author map(Record record) {
        return new Author(ID.get(record), NAME.get(record));
    }

    public static final Table<Record> TABLE = DSL.table("author");
    public static final Field<UUID> ID = DSL.field("id", UUID.class);
    public static final Field<String> NAME = DSL.field("name", String.class);
    public static final List<? extends Field<? extends Serializable>> ALL_FIELDS = List.of(ID, NAME);

    private final DSLContext jooq;
}
