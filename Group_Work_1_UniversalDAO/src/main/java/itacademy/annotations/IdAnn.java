package itacademy.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация {@code ColumnAnn} помечает поле {@code name} класса DTO, чтобы методы класса
 * {@code ReflectionUtils} при наличии такой аннотации смогли извлечь из класса DTO
 * id строки таблицы.
 * <p>Аннотация записывается в .class-файл и доступна во время выполнения через рефлексию.
 * <p>Аннотация применяется только к полям.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IdAnn {
}
