package itacademy.dto;

import itacademy.annotations.ColumnAnn;
import itacademy.annotations.IdAnn;
import itacademy.annotations.TableAnn;

/**
 * Класс DTO {@code Address} представляет собой таблицу People в БД.
 * Поля класса помечены аннотациями, чтобы методы класса {@code ReflectionUtils} при наличии
 * таких аннотаций смогли извлечь из класса {@code Address} имя таблицы, имя столбца или id строки.
 */
@TableAnn(name = "address")
public class Address {
    @IdAnn
    @ColumnAnn(name = "id")
    private Integer id;

    @ColumnAnn(name = "street")
    private String street;

    @ColumnAnn(name = "house")
    private Integer house;


    public Address(Integer id, String street, Integer house) {
        this.id = id;
        this.street = street;
        this.house = house;
    }

    public Address() {
    }

    public static AddressBuilder builder() {
        return new AddressBuilder();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouse() {
        return house;
    }

    public void setHouse(Integer house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return "Адрес: " +
                "id: " + id +
                ", улица: " + street +
                ", дом: " + house;
    }

    /**
     * Здесь используется паттерн "Строитель" для создания объекта класса DTO {@code Address}.
     * Это позволяет не заполнять обязательно все поля объекта при его создании.
     */
    public static class AddressBuilder {
        private Integer id;
        private String street;
        private Integer house;

        public AddressBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public AddressBuilder street(String street) {
            this.street = street;
            return this;
        }

        public AddressBuilder house(Integer house) {
            this.house = house;
            return this;
        }

        public Address build() {
            return new Address(id, street, house);
        }
    }
}