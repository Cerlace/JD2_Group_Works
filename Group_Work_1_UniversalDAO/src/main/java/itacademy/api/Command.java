package itacademy.api;

import itacademy.exceptions.checked.InvalidInputException;

import java.sql.SQLException;

public interface Command {
    void execute() throws SQLException, InvalidInputException;
}
