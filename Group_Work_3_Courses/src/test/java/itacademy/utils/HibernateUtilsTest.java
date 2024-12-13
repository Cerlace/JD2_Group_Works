package itacademy.utils;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

class HibernateUtilsTest {

    @Test
    void getEntityManagerTest() {
        assertInstanceOf(EntityManager.class, HibernateUtils.getEntityManager());
    }

    @Test
    void closeTest() {
        HibernateUtils.close();
        assertThrows(IllegalStateException.class, HibernateUtils::getEntityManager);
    }
}