package itacademy.api;

import itacademy.entity.Address;

/**
 * Этот интерфейс предназначен для взаимодействия с классом DTO {@code Address}. Класс DTO {@code Address} представляет
 * собой таблицу Address в БД. Данный интерфейс наследует все методы интерфейса DAO, также в этот интерфейс можно
 * добавить дополнительные методы, уникальные для класса DTO {@code Address}.
 */
public interface AddressDAO extends DAO<Address> {
}
