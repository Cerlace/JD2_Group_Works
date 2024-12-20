package itacademy.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    public static final Logger LOGGER = LoggerFactory.getLogger(HibernateUtil.class);
    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("academy");
    public static final String CREATED_NEW_ENTITY_MANAGER = "Created new Entity Manager.";
    public static final String CLOSING_ENTITY_MANAGER_FACTORY = "Closing Entity Manager Factory.";

    /**
     * Метод создает новый объект EntityManager
     * @return новый EntityManager
     */
    public static EntityManager getEntityManager() {
        LOGGER.info(CREATED_NEW_ENTITY_MANAGER);
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }

    /**
     * Метод закрывает EntityManagerFactory
     */
    public static void close() {
        LOGGER.info(CLOSING_ENTITY_MANAGER_FACTORY);
        ENTITY_MANAGER_FACTORY.close();
    }
}
