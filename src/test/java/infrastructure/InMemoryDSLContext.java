package infrastructure;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class InMemoryDSLContext {

    public static DSLContext DSL() {
        Connection connection = getConnection();
        executeDbMigrations(connection);
        return DSL.using(connection);
    }

    private static void executeDbMigrations(Connection connection) {
        try {
            List<String> files = Resources.readLines(Resources.getResource("db/migration"), Charsets.UTF_8);
            files.forEach(f -> {
                try {
                    executeUpdate(connection, Resources.toString(Resources.getResource("db/migration/" + f), Charsets.UTF_8));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:sqlite::memory:");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void executeUpdate(Connection connection, String sql) {
        try {
            connection.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}