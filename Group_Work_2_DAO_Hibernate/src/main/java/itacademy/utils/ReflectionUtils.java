package itacademy.utils;

import itacademy.exceptions.unchecked.AnnotationMissingException;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * Утилитарный класс, который содержит методы использующие рефлексию.
 */
public class ReflectionUtils {
    /**
     * Метод проверят класс на наличие аннотации {@code @TableAnn},
     * при ее наличии извлекает из нее имя таблицы.
     * Выбрасывает {@code AnnotationMissingException} в случае отсутствия аннотации.
     *
     * @param clazz класс (DTO), ассоциированный с таблицей с помощью аннотаций.
     * @param <T>   тип DTO, переданный в качестве параметра.
     * @return строку, содержащую имя таблицы в базе данных.
     */
    public static <T> String getTableNameByClass(Class<T> clazz) {
        String tableName;
        if (clazz.isAnnotationPresent(Table.class)) {
            tableName = clazz.getAnnotation(Table.class).name();

            if (tableName.isEmpty()) {
                tableName = clazz.getSimpleName();
            }

            return tableName;
        } else {
            throw new AnnotationMissingException("Ошибка! DTO - class не аннотирован @TableAnn!");
        }
    }

    public static <T> Field getIdField(T t) {
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
                return field;
            }
        }

        return null;
    }

    public static <T> void setId(T t, Serializable id) throws IllegalAccessException {
        Field idField = getIdField(t);
        if (idField != null) {
            idField.setAccessible(true);
            idField.set(t, id);
            idField.setAccessible(false);
        }
    }

    /**
     * Метод проверяет поля объекта на наличие аннотации {@code @Column},
     * при ее наличии извлекает из нее имя колонки, и добавляет его в список.
     *
     * @param clazz класс (DTO), ассоциированный с таблицей с помощью аннотаций.
     * @return список, содержащий набор имен колонок таблицы, соответствующей
     * переданному классу.
     */
    public static List<String> getColumnsNames(Class<?> clazz) {
        List<String> list = new ArrayList<>();

        List<Field> fields = getColumnsFields(clazz);

        for (Field field : fields) {
            String columnName = field.getAnnotation(Column.class).name();
            if (columnName.isEmpty()) {
                columnName = field.getName();
            }

            list.add(columnName);
        }

        return list;
    }

    public static <T> List<String> getColumnsValues(T t) throws IllegalAccessException {
        List<String> valuesList = new ArrayList<>();
        List<Field> fields = getColumnsFields(t.getClass());

        for (Field field : fields) {
            field.setAccessible(true);
            valuesList.add(String.valueOf(field.get(t)));
            field.setAccessible(false);
        }

        return valuesList;
    }

    private static List<Field> getColumnsFields(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<Field> list = new ArrayList<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                list.add(field);
            }
        }

        return list;
    }
}
