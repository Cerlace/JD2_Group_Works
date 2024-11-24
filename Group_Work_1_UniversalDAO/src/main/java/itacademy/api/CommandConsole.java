package itacademy.api;

import itacademy.exceptions.checked.InvalidInputException;

public interface CommandConsole<T> {
    T execute() throws InvalidInputException;
}
