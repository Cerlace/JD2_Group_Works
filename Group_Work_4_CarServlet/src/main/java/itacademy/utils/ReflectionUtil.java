package itacademy.utils;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Утилитарный класс, который содержит методы использующие рефлексию.
 */
public class ReflectionUtil {
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
            throw new IllegalArgumentException("Ошибка! Entity-class не аннотирован @Table!");
        }
    }

    /**
     * Метод получает у объекта поле, помеченное аннотацией @Id
     * @param t объект, поля которого проверяем
     * @return поле id
     * @param <T> тип объекта, с таблицей которого проводятся операции в БД
     */
    public static <T> Field getIdField(T t) {
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
                return field;
            }
        }

        return null;
    }

    /**
     * Метод записывает в поле, помеченное аннотацией @Id, переданное значение
     * @param t объект, которому нужно установить id
     * @param id переданный id
     * @param <T> тип объекта, с таблицей которого проводятся операции в БД
     * @throws IllegalAccessException при ошибке в работе методов рефлексии
     */
    public static <T> void setId(T t, Serializable id) throws IllegalAccessException {
        Field idField = getIdField(t);
        if (idField != null) {
            idField.setAccessible(true);
            idField.set(t, id);
            idField.setAccessible(false);
        }
    }
}
