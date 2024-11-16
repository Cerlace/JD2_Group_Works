package itacademy.dao;

import itacademy.JDBCResources;
import itacademy.api.DAO;
import itacademy.utils.SQLBuilderUtils;
import jakarta.persistence.Column;      //TODO заменить нашей аннотацией!
import jakarta.persistence.Id;          //TODO заменить нашей аннотацией!
import jakarta.persistence.Table;       //TODO заменить нашей аннотацией!

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.List;

public final class UniversalDAO<T> implements DAO<T> {

    private final Class<T> clazz;

    public UniversalDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void createTable() throws SQLException { //реализовал Данила
        String tableName = getTableName();

        String query = "CREATE TABLE IF NOT EXISTS " + tableName +
                " " + generateColumnsDescription() + ";";

        try (Connection connection = DriverManager.getConnection(JDBCResources.getURL(),
                JDBCResources.getUser(),
                JDBCResources.getPassword())) {

            Statement preparedStatement = connection.createStatement();

            preparedStatement.executeUpdate(query);
        }

    }

    @Override
    public T save(T t) throws SQLException { //реализация Ромы, Данила немного отрефакторил
        String tableName = getTableName();

        String query = "INSERT INTO " + tableName + " SET " +
                generateSetQueryPart(t) + ";";

        try (Connection connection = DriverManager.getConnection(JDBCResources.getURL(),
                JDBCResources.getUser(),
                JDBCResources.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(query,
                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    for (Field field : clazz.getDeclaredFields()) {
                        if (field.isAnnotationPresent(Id.class)) {
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

        try (Connection connection = DriverManager.getConnection(JDBCResources.getURL(),
                JDBCResources.getUser(),
                JDBCResources.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return getRecordAsObject(resultSet);
            }
        }
    }

    @Override
    public List<T> getAll() throws SQLException {
        //TODO по подобию с get, только собрать записи в List
        return List.of();
    }

    @Override
    public void update(T t) throws SQLException {
        //TODO реализовать (по подобию с методом save, только после блока SET нужно будет еще вставить id)
    }

    @Override
    public int delete(T t) throws SQLException { //реализация Ромы, Данила немного отрефакторил
        String tableName = getTableName();

        Serializable id = getIdValue(t);

        try (Connection connection = DriverManager.getConnection(JDBCResources.getURL(),
                JDBCResources.getUser(),
                JDBCResources.getPassword())) {

            String query = "DELETE FROM " + tableName + " WHERE id = " + id;
            Statement preparedStatement = connection.createStatement();

            return preparedStatement.executeUpdate(query);
        }
    }

    private String getTableName() {
        if (clazz.isAnnotationPresent(Table.class)) {
            return clazz.getAnnotation(Table.class).name();
        } else {
            throw new RuntimeException("Ошибка! DTO не аннотирован!"); //TODO создать наш exception (непроверяемый)
        }
    }

    private String generateSetQueryPart(T t) {
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            if (!fields[i].isAnnotationPresent(Id.class) && fields[i].isAnnotationPresent(Column.class)) {
                String name = fields[i].getAnnotation(Column.class).name();
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
            if (field.isAnnotationPresent(Id.class)) {
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
        throw new RuntimeException("Ошибка! DTO не аннотирован!"); //TODO создать наш exception (непроверяемый)
    }

    private String generateColumnsDescription() {
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder("(");

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(Column.class)) {
                stringBuilder
                        .append(fields[i].getAnnotation(Column.class).name())
                        .append(" ")
                        .append(SQLBuilderUtils.getSqlType(fields[i].getType().getSimpleName()));
            }
            if (fields[i].isAnnotationPresent(Id.class)) {
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
        if (resultSet.next()) {
            try {
                T object = clazz.getDeclaredConstructor().newInstance();
                for (Field field : clazz.getDeclaredFields()) {
                    field.setAccessible(true);
                    String columnLabel = field.getAnnotation(Column.class).name();
                    Object value = resultSet.getObject(columnLabel);
                    field.set(object, value);
                    field.setAccessible(false);
                }
                return object;
            } catch (InstantiationException | NoSuchMethodException |
                     InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
