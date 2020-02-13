package com.acme.bookstore.common;

import com.zaxxer.hikari.HikariDataSource;
import org.jooq.*;
import org.jooq.impl.*;

public class JooqModule {

    public Migration flyway(HikariDataSource dataSource) {
        return new Migration(dataSource);
    }

    public DSLContext dslContext(TransactionProvider provider) {
        Configuration configuration = new DefaultConfiguration()
                .set(provider)
                .set(SQLDialect.POSTGRES);
        return DSL.using(configuration);
    }

    public HikariDataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(String.format("jdbc:postgresql://%s:%s/%s", "localhost", "5432", "book"));
        dataSource.setUsername("postgres");
        dataSource.setAutoCommit(false);
        dataSource.setPassword("docker");
        return dataSource;
    }
}
