package itacademy.api;

import java.sql.SQLException;

public interface Command {
    void execute() throws SQLException;
}
