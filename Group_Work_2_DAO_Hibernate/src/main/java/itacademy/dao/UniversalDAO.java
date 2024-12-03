package itacademy.dao;

import itacademy.api.DAO;
import itacademy.utils.ExecutorUtils;
import itacademy.utils.HibernateUtils;
import itacademy.utils.ReflectionUtils;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;


/**
 * Абстрактный класс для взаимодействия с базой данных. Здесь реализованы
 * методы CRUD - создание, чтение, обновление и удаление объектов в базе данных (БД).
 * @param <T> любой класс, который представляет собой таблицу БД.
 */
public abstract class UniversalDAO<T> implements DAO<T> {
    private final Class<T> clazz;
    private final EntityManager em;

    /**
     * В конструктор передается класс Entity {@code <T>},
     * чтобы через рефлексию получить доступ к полям и аннотациями класса {@code <T>}.
     * @param clazz класс, с которым работает DAO
     */
    public UniversalDAO(Class<T> clazz) {
        this.clazz = clazz;
        this.em = HibernateUtils.getEntityManager();
    }

    /**
     * Метод добавляет в таблицу запись
     * @param t объект, поля которого нужно записать в таблицу
     * @return этот же объект с полученным от БД идентификатором
     */
    @Override
    public T save(T t) {
        return ExecutorUtils.executeHibernate(this.em, em -> {
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
        return ExecutorUtils.executeHibernate(this.em, em -> em.find(clazz, id));
    }

    /**
     * Метод получает из таблицы все записи
     * @return список объектов Entity, соответствующих таблице в БД
     */
    @Override
    public List<T> getAll() {
        String query = "FROM " + ReflectionUtils.getTableNameByClass(this.clazz);
        return ExecutorUtils.executeHibernate(this.em,
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
        ReflectionUtils.setId(t, id);
        return ExecutorUtils.executeHibernate(this.em, em -> {
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
        return Boolean.TRUE.equals(ExecutorUtils.executeHibernate(this.em, em -> {
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
        this.em.close();
    }
}
