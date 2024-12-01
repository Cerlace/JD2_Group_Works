package itacademy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

/**
 * Класс DTO {@code Address} представляет собой таблицу Address в БД.
 * Поля класса помечены аннотациями, чтобы методы класса {@code ReflectionUtils} при наличии
 * таких аннотаций смогли извлечь из класса {@code Address} имя таблицы, имя столбца или id строки.
 * Аннотации взяты из библиотек Hibernate и Lombok.
 */
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "street")
    private String street;

    @Column(name = "house")
    private Integer house;


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
}