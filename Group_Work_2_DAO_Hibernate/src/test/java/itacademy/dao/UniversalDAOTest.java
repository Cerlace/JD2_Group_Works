package itacademy.dao;

import itacademy.api.DAO;
import itacademy.dao.impl.PeopleDAOImpl;
import itacademy.entity.People;
import itacademy.utils.HibernateUtils;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UniversalDAOTest {
    private final DAO<People> dao = new PeopleDAOImpl();

    /**
     * Метод для тестирования сохранения в БД объекта
     */
    @Order(1)
    @Test
    public void saveTest() {
        People people = getPeople();

        People savedPeople = dao.save(people);
        assertNotNull(savedPeople.getId());
        assertEquals(people.getName(), savedPeople.getName());
    }

    /**
     * Метод для тестирования получения из БД объекта
     */
    @Order(2)
    @Test
    public void getTest() {
        People people = getPeople();
        People savedPeople = dao.save(people);
        People returnedPeople = dao.get(savedPeople.getId());
        assertEquals(savedPeople, returnedPeople);
    }

    /**
     * Метод для получения всех объектов в таблице
     */
    @Order(3)
    @Test
    void getAllTest() {
        People people1 = getPeople();
        dao.save(people1);
        People people2 = getPeople();
        dao.save(people2);
        List<People> allUsers = dao.getAll();
        assertEquals(2, allUsers.size());
    }

    /**
     * Метод для обновления записи в БД
     * @throws IllegalAccessException при ошибке работы методов рефлексии
     */
    @Order(4)
    @Test
    void updateTest() throws IllegalAccessException {
        People people = getPeople();
        People savedPeople = dao.save(people);
        savedPeople.setName("Updated Name");
        dao.update(savedPeople.getId(), savedPeople);

        People updatedUser = dao.get(savedPeople.getId());
        assertEquals("Updated Name", updatedUser.getName());

        People nullableUser = dao.update(updatedUser.getId() + 1, updatedUser);
        assertNull(nullableUser);
    }

    /**
     * Метод для удаления записи из БД
     */
    @Order(5)
    @Test
    void deleteTest() {
        People people = getPeople();
        People savedUser = dao.save(people);
        assertTrue(dao.delete(savedUser.getId()));
        assertFalse(dao.delete(savedUser.getId()));
    }

    @Order(6)
    @Test
    public void closeTest() {
        dao.close();
        assertThrows(IllegalStateException.class, HibernateUtils::getEntityManager);
    }

    /**
     * Метод очищает базу данных перед запуском каждого метода
     */
    @BeforeEach
    public void cleanDatabase() {
        EntityManager em = HibernateUtils.getEntityManager();

        try {
            em.getTransaction().begin();
            em.createQuery("delete from People").executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    private static People getPeople() {
        return People.builder()
                .name("Name")
                .surname("Surname")
                .age(21)
                .build();
    }
}
