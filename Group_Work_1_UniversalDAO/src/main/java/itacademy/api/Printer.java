package itacademy.api;

import java.util.List;

public interface Printer<T> {
    void printEntity(T entity);
    void printAllEntities(List<T> entities);
    void printHeader();
}
