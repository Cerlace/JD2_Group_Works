package itacademy.dao;

import itacademy.JDBCResources;
import itacademy.api.DAO;
import itacademy.utils.ReflectionUtils;
import itacademy.utils.SQLBuilderUtils;
import itacademy.annotations.ColumnAnn;
import itacademy.annotations.IdAnn;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public abstract class UniversalDAO<T> implements DAO<T> {

    private final Class<T> clazz;

    public UniversalDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void createTable() throws SQLException { //реализовал Данила
        String tableName = ReflectionUtils.getTableNameByClass(clazz);

        String query = "CREATE TABLE IF NOT EXISTS " + tableName + " " + generateColumnsDescription() + ";";

        try (Connection connection = DriverManager.getConnection(
                JDBCResources.getURL(),
                JDBCResources.getUser(),
                JDBCResources.getPassword())) {

            Statement preparedStatement = connection.createStatement();

            preparedStatement.executeUpdate(query);
        }

    }

    @Override
    public T save(T t) throws SQLException { //реализация Ромы, Данила немного отрефакторил
        String tableName = ReflectionUtils.getTableNameByClass(clazz);

        String query = "INSERT INTO " + tableName + " SET " + generateSetQueryPart(t) + ";";

        try (Connection connection = DriverManager.getConnection(
                JDBCResources.getURL(),
                JDBCResources.getUser(),
                JDBCResources.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Serializable generatedId = generatedKeys.getInt(1);
                    ReflectionUtils.setIdValue(t, generatedId);
                }
            }
        }
        return t;
    }

    @Override
    public T get(Serializable id) throws SQLException { //реализовал Данила
        String tableName = ReflectionUtils.getTableNameByClass(clazz);

        String query = "SELECT * FROM " + tableName + " WHERE id = " + id + ";";

        try (Connection connection = DriverManager.getConnection(
                JDBCResources.getURL(),
                JDBCResources.getUser(),
                JDBCResources.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    Map<String, Object> columnsAndValues = getColumnsAndValues(resultSet);
                    return ReflectionUtils.buildObject(clazz, columnsAndValues);
                } else {
                    return null;
                }
            }
        }
    }


    @Override
    public List<T> getAll() throws SQLException { //реализация от Саймона
        String tableName = ReflectionUtils.getTableNameByClass(clazz);

        String query = "SELECT * FROM " + tableName + ";";

        try (Connection connection = DriverManager.getConnection(
                JDBCResources.getURL(),
                JDBCResources.getUser(),
                JDBCResources.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                Map<String, Object> columnsAndValues = getColumnsAndValues(resultSet);
                List<T> resultList = new ArrayList<>();

                while (resultSet.next()) {
                    T record = ReflectionUtils.buildObject(clazz, columnsAndValues);
                    resultList.add(record);
                }
                return resultList;
            }
        }
    }

    @Override
    public void update(Serializable id, T t) throws SQLException { //реализация от Саймона
        String tableName = ReflectionUtils.getTableNameByClass(clazz);

        String query = "UPDATE " + tableName + " SET " + generateSetQueryPart(t) + " WHERE id = " + id + ";";

        try (Connection connection = DriverManager.getConnection(
                JDBCResources.getURL(),
                JDBCResources.getUser(),
                JDBCResources.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public int delete(Serializable id) throws SQLException { //реализация Ромы, Данила немного отрефакторил
        String tableName = ReflectionUtils.getTableNameByClass(clazz);

        try (Connection connection = DriverManager.getConnection(
                JDBCResources.getURL(),
                JDBCResources.getUser(),
                JDBCResources.getPassword())) {

            String query = "DELETE FROM " + tableName + " WHERE id = " + id;
            Statement preparedStatement = connection.createStatement();

            return preparedStatement.executeUpdate(query);
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

    private String generateColumnsDescription() {
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder("(");

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(ColumnAnn.class)) {
                stringBuilder
                        .append(fields[i].getAnnotation(ColumnAnn.class).name())
                        .append(" ")
                        .append(SQLBuilderUtils.getSqlType(fields[i].getType().getSimpleName()));
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

    private Map<String, Object> getColumnsAndValues(ResultSet resultSet) throws SQLException {
        Set<String> columns = ReflectionUtils.getColumnNames(clazz);
        Map<String, Object> columnsAndValues = new HashMap<>();
        for (String column : columns) {
            columnsAndValues.put(column, resultSet.getObject(column));
        }
        return columnsAndValues;
    }
}
