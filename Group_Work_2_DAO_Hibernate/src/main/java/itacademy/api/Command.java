package itacademy.api;

import itacademy.exceptions.checked.InvalidInputException;

@FunctionalInterface
public interface Command {
    /**
     * Метод для выполнения команд.
     * Используется паттерн команда.
     * @throws InvalidInputException при ошибке ввода данных
     * @throws IllegalAccessException при ошибке работы методов рефлексии
     */
    void execute() throws InvalidInputException, IllegalAccessException;
}
