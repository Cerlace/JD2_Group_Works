package itacademy.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация {@code ColumnAnn} помечает поле {@code name} класса DTO, чтобы методы класса
 * {@code ReflectionUtils} при наличии такой аннотации смогли извлечь из класса DTO
 * имя колонки таблицы.
 * <p>Аннотация записывается в .class-файл и доступна во время выполнения через рефлексию.
 * <p>Аннотация применяется только к методам и полям.
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnAnn {

    /**
     * Параметр {@code name} по умолчанию возвращает "" (пустая строка).
     */
    String name() default "";
}
