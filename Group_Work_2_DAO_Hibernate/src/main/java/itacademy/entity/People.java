package itacademy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, представляющий сущность "Человек" в базе данных.
 * Этот класс используется для хранения информации о людях.
 */
@Entity // Указывает, что этот класс является сущностью JPA
@Table(name = "people") // Указывает имя таблицы в базе данных
@Data // Lombok: автоматически генерирует геттеры, сеттеры, toString, equals и hashCode
@NoArgsConstructor // Lombok: генерирует конструктор без аргументов
@AllArgsConstructor // Lombok: генерирует конструктор со всеми аргументами
@Builder // Lombok: реализует паттерн Builder для создания объектов
public class People {

    /**
     * Уникальный идентификатор человека.
     */
    @Id // Обозначает первичный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматическая генерация значения
    @Column(name = "id") // Указывает имя колонки в таблице
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "age")
    private int age;

}
