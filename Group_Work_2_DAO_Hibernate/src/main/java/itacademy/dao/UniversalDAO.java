package itacademy.dao;

import itacademy.api.DAO;
import itacademy.api.SQLExecutor;
import itacademy.utils.ExecutorUtils;
import itacademy.utils.HibernateUtil;
import itacademy.utils.ReflectionUtils;
import itacademy.utils.SQLBuilderUtils;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Абстрактный класс для взаимодействия с базой данных. Здесь реализованы
 * методы CRUD - создание, чтение, обновление и удаление объектов в базе данных (БД).
 *
 * @param <T> любой класс, который представляет собой таблицу БД.
 */
public abstract class UniversalDAO<T> implements DAO<T> {

    private final Class<T> clazz;

    /**
     * В конструктор передается класс DTO {@code <T>}, чтобы через рефлексию получить доступ к полям и аннотациями класса {@code <T>}.
     */
    public UniversalDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * Метод для создания таблицы в БД, если она еще не создана
     *
     * @throws SQLException при возникновении ошибок в ходе создания таблицы
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
     * @return этот же объект с полученным от БД идентификатором
     * @throws SQLException при возникновении ошибок в ходе записи в таблицу
     * @author Рома
     */
    @Override
    public T save(T t) throws SQLException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(t);
        entityManager.getTransaction().commit();
        return t;
    }

    /**
     * Метод получает из БД запись по ее идентификатору
     *
     * @param id уникальный идентификатор
     * @return объект DTO, соответствующий таблице в БД
     * @throws SQLException при возникновении ошибок в ходе чтения из базы данных
     * @author Данила
     */
    @Override
    public T get(Serializable id) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        T myEntity = entityManager.find(clazz, id);
        return myEntity;
    }

    /**
     * Метод получает из таблицы все записи
     *
     * @return список объектов DTO, соответствующих таблице в БД
     * @throws SQLException при возникновении ошибок в ходе чтения из базы данных
     * @author Саймон
     */
    @Override
    public List<T> getAll() throws SQLException {
         /* EntityManager entityManager = HibernateUtil.getEntityManager();


        T myEntity = entityManager.find(clazz, id);
        while (myEntity != null) {


        }
        List<T> list = new ArrayList<>();
        list.add(myEntity);
        return list;*/


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
     * @param t  объект DTO, соответствующий таблице, полями которого будут заменены
     *           поля записи с идентификатором id
     * @throws SQLException при возникновении ошибок в ходе обновления данных в БД
     * @author Саймон
     */
    @Override
    public int update(Serializable id, T t) throws SQLException {
        /*EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try{
            transaction.begin();
            T existingEntity = em.find(entityClass, id);
            if (existingEntity != null) {
                em.merge(entity);
            }
            transaction.commit();*/

        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();

        T myEntity = entityManager.find(clazz, id);
        if (myEntity != null) {
            entityManager.merge(myEntity);
        }

        entityManager.getTransaction().commit();

      /*  SQLExecutor<Integer> executor = connection -> {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(SQLBuilderUtils.getUpdateQuery(id, t));
        };*/
        return (int) id;
    }

    /**
     * Метод удаляет из таблицы в БД запись с указанным идентификатором
     *
     * @param id уникальный идентификатор записи в таблице
     * @return количество удаленных записей
     * @throws SQLException при возникновении ошибок в ходе удаления данных из БД
     * @author Рома
     */
    @Override
    public int delete(Serializable id) throws SQLException { //реализация Ромы, Данила немного отрефакторил
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(clazz, id));
        entityManager.getTransaction().commit();
        return (int) id;
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
