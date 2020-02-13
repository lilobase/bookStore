package com.acme.bookstore.common;

import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;

import java.util.Collection;

public class Migration {

    private final HikariDataSource dataSource;

    public Migration(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void migrate() {
        FluentConfiguration locations = Flyway
                .configure()
                .dataSource(dataSource)
                .schemas()
                .locations("db.migration");
        new Flyway(locations).migrate();
    }
}
