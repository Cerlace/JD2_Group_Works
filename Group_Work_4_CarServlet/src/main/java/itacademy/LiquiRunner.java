package itacademy;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.core.MySQLDatabase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.exception.LiquibaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
;

public class LiquiRunner {
private static final Logger LOGGER = LoggerFactory.getLogger(LiquiRunner.class);

    public static void main(String[] args) {

        try {
            String url = "jdbc:mysql://localhost:3306/group_hw_4";
            String username = "root";
            String password = "******";

            Connection connection = DriverManager.getConnection(url, username, password);

            JdbcConnection jdbcConnection = new JdbcConnection(connection);
            Database database = new MySQLDatabase();
            database.setConnection(jdbcConnection);

            Liquibase liquibase = new Liquibase("db/changelog/db.changelog-master.xml",
                    new ClassLoaderResourceAccessor(),
                    database);

            liquibase.update("");
            LOGGER.info("Tables have been created successfully");

        } catch (LiquibaseException | SQLException e) {
            LOGGER.error("!!Error of table creating");
        }


    }
}
