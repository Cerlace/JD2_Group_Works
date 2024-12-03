package itacademy.dao;

import itacademy.api.DAO;
import itacademy.dao.impl.PeopleDAOImpl;
import itacademy.entity.People;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PeopleDAOTest {

    private final DAO<People> dao = new PeopleDAOImpl();

    /**
     * Метод для тестирования сохранения в БД объекта
     */
    @Order(1)
    @Test
    public void saveTest() {
        People people = People.builder()
                .name("Save Name")
                .surname("Save Surname")
                .age(21)
                .build();
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
        People expectedPeople = People.builder()
                .id(101)
                .name("Test 1 Name")
                .surname("Test 1 Surname")
                .age(10)
                .build();
        People returnedPeople = dao.get(expectedPeople.getId());
        assertEquals(expectedPeople, returnedPeople);
    }

    /**
     * Метод для получения всех объектов в таблице
     */
    @Order(3)
    @Test
    void getAllTest() {
        List<People> allPeople = dao.getAll();
        assertEquals(4, allPeople.size());
    }

    /**
     * Метод для обновления записи в БД
     *
     * @throws IllegalAccessException при ошибке работы методов рефлексии
     */
    @Order(4)
    @Test
    void updateTest() throws IllegalAccessException {
        People testingPeople = People.builder()
                .id(102)
                .name("Test 2 Name")
                .surname("Test 2 Surname")
                .age(20)
                .build();

        testingPeople.setName("Updated Name");

        People updatedPeople = dao.update(testingPeople.getId(), testingPeople);

        assertEquals("Updated Name", updatedPeople.getName());

        People nullablePeople = dao.update(updatedPeople.getId() + 1000, updatedPeople);
        assertNull(nullablePeople);
    }

    /**
     * Метод для удаления записи из БД
     */
    @Order(5)
    @Test
    void deleteTest() {
        People people = People.builder()
                .id(103)
                .name("Test 3 Name")
                .surname("Test 3 Surname")
                .age(30)
                .build();
        assertTrue(dao.delete(people.getId()));
        assertFalse(dao.delete(people.getId()));
    }

    @Order(6)
    @Test
    public void closeTest() {
        dao.close();
        assertNull(dao.getAll());
    }
}
