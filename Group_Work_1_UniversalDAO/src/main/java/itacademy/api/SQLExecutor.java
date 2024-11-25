package itacademy.api;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Здесь объявлен функциональный интерфейс {@code SQLExecutor}, который необходим для реализации
 * цепочки методов (стрима): подключение к БД -> формирование запроса -> вывод результата по запросу.
 * Аннотация {@code FunctionalInterface} показывает, что интерфейс может иметь не более одного абстрактного метода, что
 * позволяет его использовать для написания лямбда-выражений.
 * @param <T> любой класс DTO, который представляет собой таблицу БД.
 */
@FunctionalInterface
public interface SQLExecutor<T> {

    /**
     * Метод использует объект класса {@code Connection} для подключения к БД, создания запроса и вывода результаты
     */
    T execute(Connection connection) throws SQLException;
}
