package itacademy.dao;

import itacademy.api.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.io.Serializable;
import java.util.List;

/**
 * Абстрактный класс для взаимодействия с базой данных. Здесь реализованы
 * методы CRUD - создание, чтение, обновление и удаление объектов в базе данных (БД).
 * @param <T> любой класс, который представляет собой таблицу БД.
 */
public abstract class UniversalDAO<T> implements DAO<T> {
    private final Class<T> entityClass;
    private final EntityManagerFactory entityManagerFactory;

    public UniversalDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.entityManagerFactory = Persistence.createEntityManagerFactory("academy");
    }

    @Override
    public void save (T entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public T getById(Serializable id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<T> getAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.createQuery("SELECT ent FROM " + entityClass.getSimpleName() + " ent", entityClass).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Serializable id, T entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try{
            transaction.begin();
            T existingEntity = em.find(entityClass, id);
            if (existingEntity != null) {
                em.merge(entity);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Serializable id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            T entity = em.find(entityClass, id);
            if (entity != null) {
                em.remove(entity);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void closeEMF() {
        entityManagerFactory.close();
    }
}
