package itacademy.utils;

import itacademy.annotations.ColumnAnn;
import itacademy.annotations.IdAnn;
import itacademy.annotations.TableAnn;
import itacademy.exceptions.AnnotationMissingException;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ReflectionUtils {
    public static <T> String getTableNameByClass(Class<T> clazz) {
        if (clazz.isAnnotationPresent(TableAnn.class)) {
            return clazz.getAnnotation(TableAnn.class).name();
        } else {
            throw new AnnotationMissingException("Ошибка! DTO - class не аннотирован @TableAnn!");
        }
    }

    public static <T> List<String> getColumnNamesAndSqlTypes(Class<T> clazz) {
        List<String> columnNamesAndTypes = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(ColumnAnn.class)) {
                String columnName = fields[i].getAnnotation(ColumnAnn.class).name();

                String javaType = fields[i].getType().getSimpleName();
                String sqlType = SQLBuilderUtils.getSqlType(javaType);

                if (fields[i].isAnnotationPresent(IdAnn.class)) {
                    sqlType += " AUTO_INCREMENT PRIMARY KEY";
                }
                columnNamesAndTypes.add(columnName + " " + sqlType);
            }
        }
        return columnNamesAndTypes;
    }

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
}
