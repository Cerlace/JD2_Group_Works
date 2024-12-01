package itacademy.entity;

import lombok.*;
import jakarta.persistence.*;

/**
 * Представляет сущность Адрес в системе.
 * Этот класс сопоставляется с таблицей "address" в базе данных.
 */
@Entity
@Table(name = "address")
@Data // Генерирует геттеры, сеттеры, toString, equals и hashCode методы
@NoArgsConstructor // Генерирует конструктор без аргументов
@AllArgsConstructor // Генерирует конструктор со всеми аргументами
@Builder // Включает паттерн строителя для создания объектов
public class Address {

    /**
     * Уникальный идентификатор адреса.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматически генерирует ID
    @Column(name = "id")
    private Integer id;

    @Column(name = "street")
    private String street;

    @Column(name = "house")
    private Integer house;
}
