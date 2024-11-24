package itacademy.api;

import java.sql.SQLException;

public interface CommandDAO {
    void execute() throws SQLException;
}
