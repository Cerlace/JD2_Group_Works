package itacademy.dao;

import itacademy.JDBCResources;
import itacademy.api.DAO;
import itacademy.exceptions.AnnotationMissingException;
import itacademy.utils.SQLBuilderUtils;
import itacademy.annotations.ColumnAnn;
import itacademy.annotations.IdAnn;
import itacademy.annotations.TableAnn;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class UniversalDAO<T> implements DAO<T> {

    private final Class<T> clazz;

    public UniversalDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void createTable() throws SQLException { //реализовал Данила
        String tableName = getTableName();

        String query = "CREATE TABLE IF NOT EXISTS " + tableName + " " + generateColumnsDescription() + ";";

        try (Connection connection = DriverManager.getConnection(JDBCResources.getURL(), JDBCResources.getUser(), JDBCResources.getPassword())) {

            Statement preparedStatement = connection.createStatement();

            preparedStatement.executeUpdate(query);
        }

    }

    @Override
    public T save(T t) throws SQLException { //реализация Ромы, Данила немного отрефакторил
        String tableName = getTableName();

        String query = "INSERT INTO " + tableName + " SET " + generateSetQueryPart(t) + ";";

        try (Connection connection = DriverManager.getConnection(JDBCResources.getURL(), JDBCResources.getUser(), JDBCResources.getPassword()); PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    for (Field field : clazz.getDeclaredFields()) {
                        if (field.isAnnotationPresent(IdAnn.class)) {
                            field.setAccessible(true);
                            field.set(t, generatedKeys.getInt(1));
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return t;
    }

    @Override
    public T get(Serializable id) throws SQLException { //реализовал Данила
        String tableName = getTableName();

        String query = "SELECT * FROM " + tableName + " WHERE id = " + id + ";";

        try (Connection connection = DriverManager.getConnection(JDBCResources.getURL(), JDBCResources.getUser(), JDBCResources.getPassword()); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return getRecordAsObject(resultSet);
                } else {
                    System.out.println("Запись не найдена!");
                    return null;
                }
            }
        }
    }


    @Override
    public List<T> getAll() throws SQLException { //реализация от Саймона
        String tableName = getTableName();

        String query = "SELECT * FROM " + tableName + ";";

        try (Connection connection = DriverManager.getConnection(JDBCResources.getURL(), JDBCResources.getUser(), JDBCResources.getPassword()); PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {

            List<T> resultList = new ArrayList<>();

            while (resultSet.next()) {
                T record = getRecordAsObject(resultSet);
                if (record != null) {
                    resultList.add(record);
                }
            }
            return resultList;
        }
    }

    @Override
    public void update(T t) throws SQLException { //реализация от Саймона
        String tableName = getTableName();

        Serializable id = getIdValue(t);

        String query = "UPDATE " + tableName + " SET " + generateSetQueryPart(t) + " WHERE id = " + id + ";";

        try (Connection connection = DriverManager.getConnection(JDBCResources.getURL(), JDBCResources.getUser(), JDBCResources.getPassword()); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public int delete(T t) throws SQLException { //реализация Ромы, Данила немного отрефакторил
        String tableName = getTableName();

        Serializable id = getIdValue(t);

        try (Connection connection = DriverManager.getConnection(JDBCResources.getURL(), JDBCResources.getUser(), JDBCResources.getPassword())) {

            String query = "DELETE FROM " + tableName + " WHERE id = " + id;
            Statement preparedStatement = connection.createStatement();

            return preparedStatement.executeUpdate(query);
        }
    }

    private String getTableName() {
        // Проверяем, аннотирован ли класс аннотацией @TableAnn
        if (clazz.isAnnotationPresent(TableAnn.class)) {
            // Если аннотация присутствует, извлекаем ее значение (имя таблицы)
            return clazz.getAnnotation(TableAnn.class).name();
        } else {
            // Если аннотация отсутствует, выбрасывает собственное исключение
            throw new AnnotationMissingException("Ошибка! DTO - class не аннотирован аннотацией @TableAnn!");
        }
    }

    private String generateSetQueryPart(T t) {
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            if (!fields[i].isAnnotationPresent(IdAnn.class) && fields[i].isAnnotationPresent(ColumnAnn.class)) {
                String name = fields[i].getAnnotation(ColumnAnn.class).name();
                String value = SQLBuilderUtils.getValueToString(fields[i], t);

                stringBuilder.append(name).append(" = ").append(value);

                if (i != fields.length - 1) {
                    stringBuilder.append(", ");
                }
            }
        }
        return stringBuilder.toString();
    }

    private Serializable getIdValue(T t) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(IdAnn.class)) {
                field.setAccessible(true);
                try {
                    return (Serializable) field.get(t);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } finally {
                    field.setAccessible(false);
                }
            }
        }
        throw new AnnotationMissingException("Ошибка! DTO - class не аннотирован аннотацией @IdAnn!");
    }

    private String generateColumnsDescription() {
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder("(");

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(ColumnAnn.class)) {
                stringBuilder.append(fields[i].getAnnotation(ColumnAnn.class).name()).append(" ").append(SQLBuilderUtils.getSqlType(fields[i].getType().getSimpleName()));
            }
            if (fields[i].isAnnotationPresent(IdAnn.class)) {
                stringBuilder.append(" AUTO_INCREMENT PRIMARY KEY");
            }
            if (i != fields.length - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    private T getRecordAsObject(ResultSet resultSet) throws SQLException {
        try {
            T object = clazz.getDeclaredConstructor().newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(ColumnAnn.class)) {
                    field.setAccessible(true);
                    String columnLabel = field.getAnnotation(ColumnAnn.class).name();
                    Object value = resultSet.getObject(columnLabel);
                    field.set(object, value);
                    field.setAccessible(false);
                }
            }
            return object;
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
