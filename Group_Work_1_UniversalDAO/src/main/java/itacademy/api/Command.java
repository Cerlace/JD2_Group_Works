package itacademy.api;

import itacademy.exceptions.checked.InvalidInputException;

import java.sql.SQLException;

public interface Command {
    <T> T execute() throws SQLException, InvalidInputException;
}
