package itacademy.dao;

import itacademy.api.DAO;
import itacademy.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.SQLException;
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
    /**
     * Метод добавляет в таблицу запись
     *
     * @param t объект, поля которого нужно записать в таблицу
     * @return этот же объект с полученным от БД идентификатором
     * @throws SQLException при возникновении ошибок в ходе записи в таблицу
     * @author Рома,
     *
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
     *
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
    public List<T> getAll() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        Query query = entityManager.createQuery("select a from " + this.clazz.getSimpleName() + " a");
        List resultList1 = query.getResultList();
        return resultList1;
    }

    /**
     * Метод обновляет поля в таблице у записи с указанным идентификатором
     *
     * @param id уникальный идентификатор записи в таблице
     * @param t  объект DTO, соответствующий таблице, полями которого будут заменены
     *           поля записи с идентификатором id
     * @throws SQLException при возникновении ошибок в ходе обновления данных в БД
     * @author Саймон
     *
     */
    @Override
    public int update(Serializable id, T t) throws SQLException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();

        T myEntity = entityManager.find(clazz, id);
        if (myEntity != null) {
            updateEntity(myEntity, t);
            entityManager.merge(myEntity);
        }
        entityManager.getTransaction().commit();
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }

        return (int) id;
    }



    private <T> void updateEntity(T source, T target) {
        try {
            Field[] fields = source.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(target);
                if (value != null) {
                    field.set(source, value);
                }
                field.setAccessible(false);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
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




}
