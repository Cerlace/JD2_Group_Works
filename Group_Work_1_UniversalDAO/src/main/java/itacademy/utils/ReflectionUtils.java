package itacademy.utils;

import itacademy.annotations.ColumnAnn;
import itacademy.annotations.IdAnn;
import itacademy.annotations.TableAnn;
import itacademy.exceptions.AnnotationMissingException;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

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
        if (clazz.isAnnotationPresent(TableAnn.class)) {
            return clazz.getAnnotation(TableAnn.class).name();
        } else {
            throw new AnnotationMissingException("Ошибка! DTO - class не аннотирован @TableAnn!");
        }
    }

    /**
     * Метод проверяет поля класса на наличие аннотации {@code @ColumnAnn},
     * при ее наличии извлекает из нее имя колонки, а также тип из самого поля.
     * Полученные данные собираются в упорядоченный список. Также при наличии
     * аннотации {@code @IdAnn} добавляется строка, обозначающая PRIMARY KEY.
     *
     * @param clazz класс (DTO), ассоциированный с таблицей с помощью аннотаций.
     * @param <T>   тип DTO, переданный в качестве параметра.
     * @return упорядоченный список, содержащий строки с названиями колонок и их
     * SQL типами.
     */
    public static <T> List<String> getColumnNamesAndSqlTypes(Class<T> clazz) {
        List<String> columnNamesAndTypes = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(ColumnAnn.class)) {
                String columnName = field.getAnnotation(ColumnAnn.class).name();

                String javaType = field.getType().getSimpleName();
                String sqlType = SQLBuilderUtils.getSqlType(javaType);

                if (field.isAnnotationPresent(IdAnn.class)) {
                    sqlType += " AUTO_INCREMENT PRIMARY KEY";
                }
                columnNamesAndTypes.add(columnName + " " + sqlType);
            }
        }
        return columnNamesAndTypes;
    }

    /**
     * Метод проверяет поля объекта на наличие аннотации {@code @IdAnn},
     * при ее наличии устанавливает переданное значение в соответствующее поле.
     *
     * @param t           объект, для которого нужно установить новый id.
     * @param generatedId новый id.
     * @param <T>         тип DTO, переданный в качестве параметра.
     */
    public static <T> void setIdValue(T t, Serializable generatedId) {
        try {
            for (Field field : t.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(IdAnn.class)) {
                    field.setAccessible(true);
                    field.set(t, generatedId);
                    field.setAccessible(false);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод проверяет поля объекта на наличие аннотации {@code @ColumnAnn},
     * при ее наличии извлекает из нее имя колонки, и добавляет его во множество.
     *
     * @param clazz класс (DTO), ассоциированный с таблицей с помощью аннотаций.
     * @param <T>   тип DTO, переданный в качестве параметра.
     * @return множество, содержащее набор имен колонок таблицы, соответствующей
     * переданному классу.
     */
    public static <T> Set<String> getColumnNames(Class<T> clazz) {
        Set<String> columns = new HashSet<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ColumnAnn.class)) {
                String columnLabel = field.getAnnotation(ColumnAnn.class).name();
                columns.add(columnLabel);
            }
        }
        return columns;
    }

    /**
     * Метод создает объект переданного типа, и присваивает его полям значения,
     * переданные через параметр {@code columnsAndValues}.
     *
     * @param clazz            класс (DTO), ассоциированный с таблицей с помощью аннотаций.
     * @param <T>              тип DTO, переданный в качестве параметра.
     * @param columnsAndValues набор ключ-значение, содержащий имена колонок в качестве ключа,
     *                         и значения этих колонок из таблицы.
     * @return объект типа {@code T}, с установленными значениями.
     */
    public static <T> T buildObject(Class<T> clazz, Map<String, Object> columnsAndValues) {
        try {
            T object = clazz.getDeclaredConstructor().newInstance();

            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(ColumnAnn.class)) {
                    String columnLabel = field.getAnnotation(ColumnAnn.class).name();
                    field.setAccessible(true);
                    field.set(object, columnsAndValues.get(columnLabel));
                    field.setAccessible(false);
                }
            }
            return object;
        } catch (InstantiationException | NoSuchMethodException |
                 InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод проверяет поля объекта на наличие аннотации {@code @ColumnAnn}
     * и отсутствие аннотации {@code @IdAnn}, при соблюдении условия извлекает
     * имя колонки и значение из поля объекта, и добавляет их в список ключ-значение.
     *
     * @param t   объект, из которого извлекаются значения.
     * @param <T> тип DTO, переданный в качестве параметра.
     * @return список ключ-значение, содержащий имена колонок и их значения.
     */
    public static <T> Map<String, Object> getColumnsAndValuesFromObject(T t) {
        Map<String, Object> columnsAndValues = new HashMap<>();
        Field[] fields = t.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                if (!field.isAnnotationPresent(IdAnn.class) && field.isAnnotationPresent(ColumnAnn.class)) {
                    String columnName = field.getAnnotation(ColumnAnn.class).name();
                    field.setAccessible(true);
                    Object value = field.get(t);
                    field.setAccessible(false);
                    columnsAndValues.put(columnName, value);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return columnsAndValues;
    }
}
