package itacademy;

import itacademy.api.DAO;
import itacademy.dao.UniversalDAO;
import itacademy.dto.Address;
import itacademy.dto.People;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        DAO<People> peopleDAO = new UniversalDAO<>(People.class);
        People p1 = new People(1,"Роман", "Гуляко", 28);
        People p2 = peopleDAO.save(p1);
        System.out.println(p2);

        People p3 = peopleDAO.get(p2.getId());
        System.out.println(p3);

        peopleDAO.delete(p2);

        UniversalDAO<Address> addressDAO = new UniversalDAO<>(Address.class);
        Address a1 = new Address(1, "Независимости", 1);
        Address a2 = addressDAO.save(a1);
        System.out.println(a2);

        Address a3 = addressDAO.get(a2.getId());
        System.out.println(a3);

        addressDAO.delete(a2);
    }
}
