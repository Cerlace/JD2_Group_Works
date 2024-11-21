package itacademy.dao;

import itacademy.api.DAO;
import itacademy.api.SQLExecutor;
import itacademy.utils.ExecutorUtils;
import itacademy.utils.ReflectionUtils;
import itacademy.utils.SQLBuilderUtils;

import java.io.Serializable;
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

    /**
     * Метод для создания таблицы в БД, если она еще не создана
     *
     * @throws SQLException при возникновении ошибок в ходе создания таблицы
     *
     * @author Данила
     */
    @Override
    public void createTable() throws SQLException {
        SQLExecutor<T> executor = connection -> {
            Statement statement = connection.createStatement();
            statement.executeUpdate(SQLBuilderUtils.getCreateTableQuery(this.clazz));

            return null;
        };

        ExecutorUtils.executeSQL(executor);
    }

    /**
     * Метод добавляет в таблицу запись
     *
     * @param t объект, поля которого нужно записать в таблицу
     *
     * @return этот же объект с полученным от БД идентификатором
     *
     * @throws SQLException при возникновении ошибок в ходе записи в таблицу
     *
     * @author Рома
     */
    @Override
    public T save(T t) throws SQLException {
        SQLExecutor<T> executor = connection -> {
            PreparedStatement statement = connection.prepareStatement(SQLBuilderUtils.getInsertQuery(t),
                    Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Serializable generatedId = generatedKeys.getInt(1);
                    ReflectionUtils.setIdValue(t, generatedId);
                }
            }

            return t;
        };

        return ExecutorUtils.executeSQL(executor);
    }

    /**
     * Метод получает из БД запись по ее идентификатору
     *
     * @param id уникальный идентификатор
     *
     * @return объект DTO, соответствующий таблице в БД
     *
     * @throws SQLException при возникновении ошибок в ходе чтения из базы данных
     *
     * @author Данила
     */
    @Override
    public T get(Serializable id) throws SQLException {
        SQLExecutor<T> executor = connection -> {
            Statement preparedStatement = connection.createStatement();

            try (ResultSet resultSet = preparedStatement.executeQuery(SQLBuilderUtils.getSelectQuery(id,this.clazz))) {
                if (resultSet.next()) {
                    Map<String, Object> columnsAndValues = getColumnsAndValuesFromRecord(resultSet);
                    return ReflectionUtils.buildObject(clazz, columnsAndValues);
                } else {
                    return null;
                }
            }
        };

        return ExecutorUtils.executeSQL(executor);
    }

    /**
     * Метод получает из таблицы все записи
     *
     * @return список объектов DTO, соответствующих таблице в БД
     *
     * @throws SQLException при возникновении ошибок в ходе чтения из базы данных
     *
     * @author Саймон
     */
    @Override
    public List<T> getAll() throws SQLException {
        SQLExecutor<List<T>> executor = connection -> {
            Statement statement = connection.createStatement();

            try (ResultSet resultSet = statement.executeQuery(SQLBuilderUtils.getSelectAllQuery(this.clazz))) {

                List<T> resultList = new ArrayList<>();

                while (resultSet.next()) {
                    Map<String, Object> columnsAndValues = getColumnsAndValuesFromRecord(resultSet);
                    T record = ReflectionUtils.buildObject(clazz, columnsAndValues);
                    resultList.add(record);
                }
                return resultList;
            }
        };

        return ExecutorUtils.executeSQL(executor);
    }

    /**
     * Метод обновляет поля в таблице у записи с указанным идентификатором
     *
     * @param id уникальный идентификатор записи в таблице
     * @param t объект DTO, соответствующий таблице, полями которого будут заменены
     *          поля записи с идентификатором id
     *
     * @throws SQLException при возникновении ошибок в ходе обновления данных в БД
     *
     * @author Саймон
     */
    @Override
    public void update(Serializable id, T t) throws SQLException {
        SQLExecutor<T> executor = connection -> {
            Statement statement = connection.createStatement();
            statement.executeUpdate(SQLBuilderUtils.getUpdateQuery(id,t));

            return null;
        };

        ExecutorUtils.executeSQL(executor);
    }

    /**
     * Метод удаляет из таблицы в БД запись с указанным идентификатором
     *
     * @param id уникальный идентификатор записи в таблице
     *
     * @return количество удаленных записей
     *
     * @throws SQLException при возникновении ошибок в ходе удаления данных из БД
     *
     * @author Рома
     */
    @Override
    public int delete(Serializable id) throws SQLException { //реализация Ромы, Данила немного отрефакторил
        SQLExecutor<Integer> executor = connection -> {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(SQLBuilderUtils.getDeleteQuery(id,this.clazz));
        };

        return ExecutorUtils.executeSQL(executor);
    }

    private Map<String, Object> getColumnsAndValuesFromRecord(ResultSet resultSet) throws SQLException {
        Set<String> columns = ReflectionUtils.getColumnNames(clazz);
        Map<String, Object> columnsAndValues = new HashMap<>();
        for (String column : columns) {
            columnsAndValues.put(column, resultSet.getObject(column));
        }
        return columnsAndValues;
    }
}
