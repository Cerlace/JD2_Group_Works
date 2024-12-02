package itacademy.api;

import itacademy.exceptions.checked.InvalidInputException;

import java.sql.SQLException;

@FunctionalInterface
public interface Command {
    /**
     * Метод для выполнения команд.
     * Используется паттерн команда.
     * @throws SQLException при ошибке в работе с базой данных
     * @throws InvalidInputException при ошибке ввода данных
     * @throws IllegalAccessException при ошибке работы методов рефлексии
     */
    void execute() throws SQLException, InvalidInputException, IllegalAccessException;
}
