package itacademy.api;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface SQLExecutor<T> {
    T execute(Connection connection) throws SQLException;
}
