package itacademy.dao.impl;

import itacademy.api.PeopleDAO;
import itacademy.dao.UniversalDAO;
import itacademy.dto.People;

/**
 * Класс {@code PeopleDAOImpl} наследуется от абстрактного класса {@code UniversalDAO} для того,
 *  чтобы его методы работали конкретно с классом DTO {@code People}. Класс {@code PeopleDAOImpl} также
 * реализует дополнительные методы из интерфейса {@code PeopleDAO}, уникальные для класса DTO {@code People}.
 */
public class PeopleDAOImpl extends UniversalDAO<People> implements PeopleDAO {

    /**
     * Этот конструктор передает в конструктор родительского класса {@code UniversalDAO} класс DTO {@code People},
     * чтобы через рефлексию получить доступ к полям и аннотациями класса DTO {@code People}.
     */
    public PeopleDAOImpl() {
        super(People.class);
    }
}
