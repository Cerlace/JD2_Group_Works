package itacademy.api;

import itacademy.entity.People;

/**
 * Этот интерфейс предназначен для взаимодействия с классом DTO {@code People}. Класс DTO {@code People} представляет
 * собой таблицу People в БД. Данный интерфейс наследует все методы интерфейса DAO, также в этот интерфейс можно
 * добавить дополнительные методы, уникальные для класса DTO {@code People}.
 */
public interface PeopleDAO extends DAO<People> {
}
