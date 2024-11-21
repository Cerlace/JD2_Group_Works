package itacademy.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class SQLBuilderUtils {

    public static <T> String generateSetQueryPart(T t) {
        Map<String, Object> columnsAndValues = ReflectionUtils.getColumnsAndValuesFromObject(t);
        StringBuilder stringBuilder = new StringBuilder();
        for (String columnName : columnsAndValues.keySet()) {
            String value = SQLBuilderUtils.getValueToString(columnsAndValues.get(columnName));

            stringBuilder.append(columnName).append(" = ").append(value);

            stringBuilder.append(", ");
        }
        stringBuilder.delete(stringBuilder.lastIndexOf(", "), stringBuilder.length());

        return stringBuilder.toString();
    }

    public static <T> String generateColumnsDescription(Class<T> clazz) {
        List<String> columnsAndTypes = ReflectionUtils.getColumnNamesAndSqlTypes(clazz);
        StringBuilder stringBuilder = new StringBuilder("(");

        for (int i = 0; i < columnsAndTypes.size(); i++) {
            stringBuilder.append(columnsAndTypes.get(i));
            if (i != columnsAndTypes.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public static String getValueToString(Object value) {
        String returnTypeName = value.getClass().getSimpleName();

        String result = value.toString();
        switch (returnTypeName) {
            case "Integer":
            case "int":
            case "Double":
            case "double":
            case "Long":
            case "long":
            case "Boolean":
            case "boolean":
            case "Byte":
            case "byte": {
                return result;
            }
            default: {
                return String.format("'%s'", result);
            }
        }
    }

    public static String getSqlType(String javaType) {
        switch (javaType) {
            case "Integer":
            case "int":
                return "INT";
            case "Double":
            case "double":
                return "DOUBLE";
            case "Long":
            case "long":
                return "BIGINT";
            case "Boolean":
            case "boolean":
                return "BOOLEAN";
            case "Byte":
            case "byte":
                return "TINYINT";
            case "String":
                return "VARCHAR(255)";
            case "Character":
            case "char":
                return "CHAR(1)";
            case "Date":
                return "DATE";
            case "Time":
                return "TIME";
            default:
                return "";
        }
    }

    public static String getCreateTableQuery(Class<?> clazz) {
        return "CREATE TABLE IF NOT EXISTS " +
                ReflectionUtils.getTableNameByClass(clazz) +
                " " + generateColumnsDescription(clazz);
    }

    public static <T> String getInsertQuery(T t) {
        return "INSERT INTO " + ReflectionUtils.getTableNameByClass(t.getClass())
                + " SET " + generateSetQueryPart(t);
    }

    public static String getSelectQuery(Serializable id, Class<?> clazz) {
        return "SELECT * FROM " + ReflectionUtils.getTableNameByClass(clazz) + " WHERE id = " + id;
    }

    public static String getSelectAllQuery(Class<?> clazz) {
        return "SELECT * FROM " + ReflectionUtils.getTableNameByClass(clazz);
    }

    public static <T> String getUpdateQuery(Serializable id, T t) {
        return  "UPDATE " + ReflectionUtils.getTableNameByClass(t.getClass())
                + " SET " + generateSetQueryPart(t) + " WHERE id = " + id;
    }

    public static String getDeleteQuery(Serializable id, Class<?> clazz) {
        return "DELETE FROM " + ReflectionUtils.getTableNameByClass(clazz) + " WHERE id = " + id;
    }
}
