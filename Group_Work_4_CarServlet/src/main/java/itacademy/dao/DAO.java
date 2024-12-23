package itacademy.dao;

import java.io.Serializable;
import java.util.List;

public interface DAO<T> {
    /**
     * Метод сохраняет новую строку таблицы в БД. Строка может содержать различные параметры.
     * @param t строка таблицы, представленная объектом класса Entity {@code <T>}.
     * @return сохраненный объект с присвоенным ему id
     */
    T save(T t);

    /**
     * Метод возвращает список всех строк таблицы в том порядке, в котором они представлены в таблице БД
     * @return список всех объектов, хранящихся в таблице
     */
    List<T> getAll();

    /**
     * Метод обновляет параметры строки таблицы БД по идентификатору строки {@code id}.
     * @param id - идентификатор строки таблицы БД
     * @param t - обновленные параметры строки таблицы БД
     * @throws IllegalAccessException при ошибке работы методов рефлексии
     * @return обновленный в БД объект или null, если объект с таким id отсутствует
     */
    T update(Serializable id, T t) throws IllegalAccessException;

    /**
     * Метод удаляет строку таблицы БД по идентификатору строки {@code id}.
     * @param id идентификатор строки таблицы БД
     * @return true - если запись удалена успешно, false - если запись с таким id не была найдена
     */
    boolean delete(Serializable id);

    /**
     * Метод для закрытия EntityManager и EntityManagerFactory
     */
    void close();
}
