package itacademy;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseConnection;
import liquibase.database.core.MySQLDatabase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.exception.LiquibaseException;
import javax.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.sql.Connection;
import java.sql.DriverManager;

public class LiquibaseRunner {

    public static void main(String[] args) throws LiquibaseException {
        // Параметры подключения к базе данных MySQL
        String url = "jdbc:mysql://localhost:3306/hw6_db";
        String user = "root";
        String password = "Лщзфпфифтф_7";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            JdbcConnection jdbcConnection = new JdbcConnection(connection);
            Database database = new MySQLDatabase();
            database.setConnection(jdbcConnection);

            Liquibase liquibase = new Liquibase("changelogs/changelog.xml", new ClassLoaderResourceAccessor(), database);
            liquibase.update("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

