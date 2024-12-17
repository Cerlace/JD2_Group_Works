package itacademy.dao;

import itacademy.api.IDAO;
import itacademy.utils.ExecutorUtils;
import itacademy.utils.HibernateUtils;
import itacademy.utils.ReflectionUtils;
import org.slf4j.Logger;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public abstract class DAO<T> implements IDAO<T> {
    private static final String SAVE_LOG_MESSAGE = "Table: '{}', start saving object {}";
    private static final String UPDATE_LOG_MESSAGE = "Table: '{}', start updating row with id = {}";
    private static final String DELETE_LOG_MESSAGE = "Table: '{}', start deleting row with id = {}";
    private static final String GET_LOG_MESSAGE = "Table: '{}', start getting row with id = {}";
    private static final String GET_ALL_LOG_MESSAGE = "Table: '{}', start getting all rows";
    private static final String CLOSING_SESSION_MESSAGE = "Closing session";

    private final Logger logger;
    private final Class<T> clazz;
    private final EntityManager entityManager;
    private final String tableName;

    /**
     * В конструктор передается класс Entity {@code <T>},
     * чтобы через рефлексию получить доступ к полям и аннотациями класса {@code <T>}.
     * @param clazz класс, с которым работает DAO
     * @param logger объект logger соответствующего класса
     */
    public DAO(Class<T> clazz, Logger logger) {
        this.clazz = clazz;
        this.entityManager = HibernateUtils.getEntityManager();
        this.logger = logger;
        this.tableName = ReflectionUtils.getTableNameByClass(clazz);
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Метод добавляет в таблицу запись
     * @param t объект, поля которого нужно записать в таблицу
     * @return этот же объект с полученным от БД идентификатором
     */
    @Override
    public T save(T t) {
        logger.info(SAVE_LOG_MESSAGE, this.tableName, t);

        return ExecutorUtils.executeHibernate(this.entityManager, em -> {
            em.persist(t);
            return t;
        });
    }

    /**
     * Метод получает из БД запись по ее идентификатору
     * @param id уникальный идентификатор
     * @return объект Entity, соответствующий таблице в БД
     */
    @Override
    public T get(Serializable id) {
        logger.info(GET_LOG_MESSAGE, this.tableName, id);

        return ExecutorUtils.executeHibernate(this.entityManager, em -> em.find(clazz, id));
    }

    /**
     * Метод получает из таблицы все записи
     * @return список объектов Entity, соответствующих таблице в БД
     */
    @Override
    public List<T> getAll() {
        logger.info(GET_ALL_LOG_MESSAGE, this.tableName);

        String query = "FROM " + this.tableName;
        return ExecutorUtils.executeHibernate(this.entityManager,
                em -> em.createQuery(query, clazz).getResultList());
    }

    /**
     * Метод обновляет поля в таблице у записи с указанным идентификатором
     * @param id уникальный идентификатор записи в таблице
     * @param t объект Entity, соответствующий таблице, полями которого будут заменены
     * поля записи с идентификатором id
     */
    @Override
    public T update(Serializable id, T t) throws IllegalAccessException {
        logger.info(UPDATE_LOG_MESSAGE, this.tableName, id);

        ReflectionUtils.setId(t, id);
        return ExecutorUtils.executeHibernate(this.entityManager, em -> {
            T updatedEntity;
            if ((updatedEntity = em.find(this.clazz, id)) != null) {
                updatedEntity = em.merge(t);
            }

            return updatedEntity;
        });
    }

    /**
     * Метод удаляет из таблицы в БД запись с указанным идентификатором
     * @param id уникальный идентификатор записи в таблице
     * @return количество удаленных записей
     */
    @Override
    public boolean delete(Serializable id) {
        logger.info(DELETE_LOG_MESSAGE, this.tableName, id);

        return Boolean.TRUE.equals(ExecutorUtils.executeHibernate(this.entityManager, em -> {
            T t = em.find(this.clazz, id);
            if (t != null) {
                em.remove(t);
                return true;
            } else {
                return false;
            }
        }));
    }

    @Override
    public void close() {
        logger.info(CLOSING_SESSION_MESSAGE);
        this.entityManager.close();
    }
}
