package itacademy.utils;

import itacademy.JDBCResources;
import itacademy.api.SQLExecutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ExecutorUtils {
    public static  <T> T executeSQL(SQLExecutor<T> sqlExecutor) {
        try (Connection connection = DriverManager.getConnection(JDBCResources.getURL(),
                JDBCResources.getUser(),
                JDBCResources.getPassword());) {
            return sqlExecutor.execute(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
