package itacademy.api;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface DAO <T>{
    void createTable() throws SQLException;

    T save(T t) throws SQLException;

    T get(Serializable id) throws SQLException ;

    List<T> getAll() throws SQLException;

    void update(T t) throws SQLException;

    int delete(T t) throws SQLException;
}
