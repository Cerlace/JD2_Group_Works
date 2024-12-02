package itacademy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;


/**
 * Класс DTO {@code Address} представляет собой таблицу People в БД.
 * Поля класса помечены аннотациями, чтобы методы класса {@code ReflectionUtils} при наличии
 * таких аннотаций смогли извлечь из класса {@code Address} имя таблицы, имя столбца или id строки.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String street;

    @Column
    private Integer house;
}