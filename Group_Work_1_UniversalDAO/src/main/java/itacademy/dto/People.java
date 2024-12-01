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
 * Класс DTO {@code People} представляет собой таблицу People в БД.
 * Поля класса помечены аннотациями, чтобы методы класса {@code ReflectionUtils} при наличии
 * таких аннотаций смогли извлечь из класса {@code People} имя таблицы, имя столбца или id строки.
 * Аннотации взяты из библиотек Hibernate и Lombok.
 */
@Table(name = "people")
public class People {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "age")
    private int age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Человек: " +
                "id: " + id +
                ", имя: " + name +
                ", фамилия: " + surname +
                ", возраст: " + age;
    }
}
