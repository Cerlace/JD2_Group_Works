package itacademy.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SQLBuilderUtils {
    public static <T> String getValueToString(Field field, T t) { //реализация Ромы, Данила немного отрефакторил
        field.setAccessible(true);
        String returnTypeName = field.getType().getSimpleName();
        try {
            String result = field.get(t).toString();
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
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            field.setAccessible(false);
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
}
