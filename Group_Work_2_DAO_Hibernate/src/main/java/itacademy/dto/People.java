package itacademy.dto;

import itacademy.annotations.ColumnAnn;
import itacademy.annotations.IdAnn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Класс DTO {@code People} представляет собой таблицу People в БД.
 * Поля класса помечены аннотациями, чтобы методы класса {@code ReflectionUtils} при наличии
 * таких аннотаций смогли извлечь из класса {@code People} имя таблицы, имя столбца или id строки.
 */
@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
public class People {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String name;

    @Column    private String surname;

    @Column    private int age;

    public static PeopleBuilder builder() {
        return new PeopleBuilder();
    }

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

    /**
     * Здесь используется паттерн "Строитель" для создания объекта класса DTO {@code People}.
     * Это позволяет не заполнять обязательно все поля объекта при его создании.
     */
    public static class PeopleBuilder {
        private Integer id;
        private String name;
        private String surname;
        private int age;

        public PeopleBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public PeopleBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PeopleBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public PeopleBuilder age(int age) {
            this.age = age;
            return this;
        }

        public People build() {
            return new People(this.id, this.name, this.surname, this.age);
        }
    }
}
