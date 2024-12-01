package itacademy.api;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface DAO <T>{
    void save(T entity);
    T getById(Serializable id);
    List<T> getAll();
    void update(Serializable id, T entity);
    void delete(Serializable id);
}
