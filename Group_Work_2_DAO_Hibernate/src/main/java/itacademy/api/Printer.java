package itacademy.api;

import java.util.List;

public interface Printer<T> {
    /**
     * Метод для печати объекта
     * @param entity объект, который необходимо напечатать
     * @throws IllegalAccessException при ошибке работы методов рефлексии
     */
    void printEntity(T entity) throws IllegalAccessException;

    /**
     * Метод для печати списка объектов
     * @param entities список объектов, которые необходимо напечатать
     * @throws IllegalAccessException при ошибке работы методов рефлексии
     */
    void printAllEntities(List<T> entities) throws IllegalAccessException;

    /**
     * Метод для печати заголовка таблицы
     */
    void printHeader();
}
