package itacademy.dao;

import itacademy.api.DAO;
import itacademy.dao.impl.AddressDAOImpl;
import itacademy.entity.Address;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddressDAOTest {

    private final DAO<Address> dao = new AddressDAOImpl();

    /**
     * Метод для тестирования сохранения в БД объекта
     */
    @Order(1)
    @Test
    public void saveTest() {
        Address address = Address.builder()
                .street("Save Street")
                .house(5)
                .build();
        Address savedAddress = dao.save(address);
        assertNotNull(savedAddress.getId());
        assertEquals(address.getStreet(), savedAddress.getStreet());
    }

    /**
     * Метод для тестирования получения из БД объекта
     */
    @Order(2)
    @Test
    public void getTest() {
        Address expectedAddress = Address.builder()
                .id(101)
                .street("Test 1 Street")
                .house(10)
                .build();
        Address returnedAddress = dao.get(expectedAddress.getId());
        assertEquals(expectedAddress, returnedAddress);
    }

    /**
     * Метод для получения всех объектов в таблице
     */
    @Order(3)
    @Test
    void getAllTest() {
        List<Address> allAddresses = dao.getAll();
        assertEquals(4, allAddresses.size());
    }

    /**
     * Метод для обновления записи в БД
     *
     * @throws IllegalAccessException при ошибке работы методов рефлексии
     */
    @Order(4)
    @Test
    void updateTest() throws IllegalAccessException {
        Address testingAddress = Address.builder()
                .id(102)
                .street("Test 2 Street")
                .house(20)
                .build();

        testingAddress.setStreet("Updated Street");

        Address updatedAddress = dao.update(testingAddress.getId(), testingAddress);

        assertEquals("Updated Street", updatedAddress.getStreet());

        Address nullablePeople = dao.update(updatedAddress.getId() + 1000, updatedAddress);
        assertNull(nullablePeople);
    }

    /**
     * Метод для удаления записи из БД
     */
    @Order(5)
    @Test
    void deleteTest() {
        Address address = Address.builder()
                .id(103)
                .street("Test 3 Street")
                .house(30)
                .build();
        assertTrue(dao.delete(address.getId()));
        assertFalse(dao.delete(address.getId()));
    }

    @Order(6)
    @Test
    public void closeTest() {
        dao.close();
        assertNull(dao.getAll());
    }
}
