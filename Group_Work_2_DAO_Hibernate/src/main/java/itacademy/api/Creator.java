package itacademy.api;

import itacademy.exceptions.checked.InvalidInputException;

@FunctionalInterface
public interface Creator<T> {
    /**
     * Метод для генерации объекта (Аналог метода get() интерфейса Supplier)
     * @return созданный объект
     * @throws InvalidInputException при ошибках ввода информации
     */
    T create() throws InvalidInputException;
}
