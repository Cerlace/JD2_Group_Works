package itacademy.api;

import javax.persistence.EntityManager;

/**
 * Здесь объявлен функциональный интерфейс {@code HibernateExecutor}, который необходим для реализации
 * цепочки методов (стрима): подключение к БД -> формирование запроса -> вывод результата по запросу.
 * Аннотация {@code FunctionalInterface} показывает, что интерфейс может иметь не более одного абстрактного метода, что
 * позволяет его использовать для написания лямбда-выражений.
 * @param <T> тип данных, который необходимо вернуть методом
 */
@FunctionalInterface
public interface HibernateExecutor<T> {

    /**
     * Метод выполняет манипуляции с данными в БД
     * @param em объект EntityManager, с помощью которого происходит общение с БД
     * @return результат выполнения операции
     */
    T execute(EntityManager em);
}
