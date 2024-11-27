package itacademy.dao.impl;

import itacademy.api.AddressDAO;
import itacademy.dao.UniversalDAO;
import itacademy.dto.Address;

/**
 * Класс {@code AddressDAOImpl} наследуется от абстрактного класса {@code UniversalDAO} для того,
 *  чтобы его методы работали конкретно с классом DTO {@code Address}. Класс {@code AddressDAOImpl} также
 * реализует дополнительные методы из интерфейса {@code AddressDAO}, уникальные для класса DTO {@code Address}.
 */
public class AddressDAOImpl extends UniversalDAO<Address> implements AddressDAO {

    /**
     * Этот конструктор передает в конструктор родительского класса {@code UniversalDAO} класс DTO {@code Address},
     * чтобы через рефлексию получить доступ к полям и аннотациями класса DTO {@code Address}.
     */
    public AddressDAOImpl() {
        super(Address.class);
    }
}
