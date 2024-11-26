package itacademy.api;

import itacademy.exceptions.checked.InvalidInputException;

public interface Creator<T> {
    T create() throws InvalidInputException;
}
