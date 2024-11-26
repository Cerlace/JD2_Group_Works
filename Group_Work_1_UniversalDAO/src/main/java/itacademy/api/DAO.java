package itacademy.api;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Интерфейс для взаимодействия с базой данных. Включает в себя
 * методы CRUD - создание, чтение, обновление и удаление объектов в базе данных (БД).
 * @param <T> любой класс DTO, который представляет собой таблицу БД.
 */
public interface DAO <T>{

    /**
     * Метод создает таблицу в БД с названием как у класса {@code <T>}.
     * @throws SQLException при ошибках создания таблицы.
     */
    void createTable() throws SQLException;

    /**
     * Метод сохраняет новую строку таблицы в БД. Строка может содержать различные параметры.
     * @param t строка таблицы, представленная объектом класса DTO {@code <T>}.
     * @throws SQLException при ошибках записи данных в таблицу.
     */
    T save(T t) throws SQLException;

    /**
     * Метод возвращает параметры строки таблицы по идентификатору строки {@code id}.
     * <p>Интерфейс {@code Serializable} используется, чтобы идентификатор мог быть любым сериализуемым типом
     * (такой тип можно представить как последовательность байт, например Integer или String).
     * @param id идентификатор строки таблицы БД
     * @throws SQLException при ошибках чтения данных из таблицы.
     */
    T get(Serializable id) throws SQLException ;

    /**
     * Метод возвращает список всех строк таблицы в том порядке, в котором они представлены в таблице БД
     * @throws SQLException при ошибках чтения данных из таблицы.
     */
    List<T> getAll() throws SQLException;
    /**
     * Метод обновляет параметры строки таблицы БД по идентификатору строки {@code id}.
     * @param id - идентификатор строки таблицы БД
     * @param t - обновленные параметры строки таблицы БД
     * @throws SQLException при ошибках обновления данных в таблице.
     */
    int update(Serializable id, T t) throws SQLException;

    /**
     * Метод удаляет строку таблицы БД по идентификатору строки {@code id}.
     * @param id идентификатор строки таблицы БД
     * @throws SQLException при ошибках удаления данных из таблицы.
     */
    int delete(Serializable id) throws SQLException;
}
