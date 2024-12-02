package itacademy.utils;

import itacademy.api.HibernateExecutor;

import javax.persistence.EntityManager;

public class ExecutorUtils {
    public static <T> T executeHibernate(HibernateExecutor<T> hibernateExecutor) {
        EntityManager em = HibernateUtils.getEntityManager();
        em.getTransaction().begin();
        T t = hibernateExecutor.execute(em);
        em.getTransaction().commit();
        em.close();

        return t;
    }
}
