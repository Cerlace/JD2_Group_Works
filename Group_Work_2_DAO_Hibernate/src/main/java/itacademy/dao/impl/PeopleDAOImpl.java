package itacademy.dao.impl;

import itacademy.api.PeopleDAO;
import itacademy.dao.UniversalDAO;
import itacademy.entity.People;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс {@code PeopleDAOImpl} наследуется от абстрактного класса {@code UniversalDAO} для того,
 *  чтобы его методы работали конкретно с классом DTO {@code People}. Класс {@code PeopleDAOImpl} также
 * реализует дополнительные методы из интерфейса {@code PeopleDAO}, уникальные для класса DTO {@code People}.
 */
public class PeopleDAOImpl extends UniversalDAO<People> implements PeopleDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeopleDAOImpl.class);
    /**
     * Этот конструктор передает в конструктор родительского класса {@code UniversalDAO} класс DTO {@code People},
     * чтобы через рефлексию получить доступ к полям и аннотациями класса DTO {@code People},
     * а также устанавливает logger класса.
     */
    public PeopleDAOImpl() {
        super(People.class, LOGGER);
    }
}
