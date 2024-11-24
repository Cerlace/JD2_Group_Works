package itacademy.api;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Интерфейс для взаимодействия с базой данных. Включает в себя
 * методы CRUD - создание, чтение, обновление и удаление объектов в базе данных (БД).
 * @param <T> любой класс, который представляет собой таблицу в базе данных.
 */
public interface DAO <T>{

    /**
     * Метод создает таблицу в БД с названием как у класса {@code <T>}.
     * @throws SQLException при ошибках взаимодействия с БД.
     */
    void createTable() throws SQLException;

    /**
     * Метод сохраняет новую строку таблицы в БД. Строка может содержать различные параметры.
     * @param t строка таблицы, представленная объектом класса {@code <T>}.
     * @throws SQLException при ошибках взаимодействия с БД.
     */
    T save(T t) throws SQLException;

    /**
     * Метод возвращает параметры строки таблицы по идентификатору строки {@code id}.
     * <p>Интерфейс {@code Serializable} используется, чтобы идентификатор мог быть любым сериализуемым типом
     * (такой тип можно представить как последовательность байт, например Integer или String).
     * @param id идентификатор строки таблицы БД
     * @throws SQLException при ошибках взаимодействия с БД.
     */
    T get(Serializable id) throws SQLException ;

    /**
     * Метод возвращает список всех строк таблицы в том порядке, в котором они представлены в таблице БД
     * @throws SQLException при ошибках взаимодействия с БД.
     */
    List<T> getAll() throws SQLException;

    /**
     * Метод обновляет параметры строки таблицы БД по идентификатору строки {@code id}.
     * @param id - идентификатор строки таблицы БД
     * @param t - обновленные параметры строки таблицы БД
     * @throws SQLException при ошибках взаимодействия с БД.
     */
    void update(Serializable id, T t) throws SQLException;

    /**
     * Метод удаляет строку таблицы БД по идентификатору строки {@code id}.
     * @param id идентификатор строки таблицы БД
     * @throws SQLException при ошибках взаимодействия с БД.
     */
    int delete(Serializable id) throws SQLException;
}
