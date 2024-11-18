package itacademy.utils;

import itacademy.api.DAO;
import itacademy.dao.UniversalDAO;
import itacademy.dto.Address;
import itacademy.dto.People;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UtilityShowInfo {
    DAO<People> peopleDAO = new UniversalDAO<>(People.class);
    //People p1 = getPeople(, "Роман", "Гуляко", 28);

    public void savePeople(int id, String name, String surname, int age) throws SQLException {
        System.out.println("Метод save");
        People p1 = new People(id, name, surname, age);
        People p0 = peopleDAO.save(p1);
        System.out.println(p0);
    }

    public People getPeople(int id) throws SQLException {
        System.out.println("Метод get");
        People p = peopleDAO.get((Integer)id);
        return p;
    }

    public void getAllPeople() throws SQLException {
        System.out.println("Метод getAll");
        for (People p : peopleDAO.getAll()) {
            System.out.println(p);
        }
    }

    public void updatePeople(int id, String name, String surname, int age) throws SQLException {
        System.out.println("Метод update");
        People p = peopleDAO.get(id);
        p.setName(name);
        p.setSurname(surname);
        p.setAge(age);
        peopleDAO.update(p);
        System.out.println(p);
    }

    public void deletePeople(int id) throws SQLException {
        System.out.println("Метод delete");
        peopleDAO.delete(peopleDAO.get(id));
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////
    UniversalDAO<Address> addressDAO = new UniversalDAO<>(Address.class);
    public void saveAddress(int id, String street, int house) throws SQLException {
        System.out.println("Метод save");
        Address a1 = new Address(id, street, house);
        Address a2 = addressDAO.save(a1);
        System.out.println(a2);
    }

    public void getAddress(int id) throws SQLException {
        System.out.println("Метод get");
        Address a = addressDAO.get((Integer)id);
        System.out.println(a);
    }

    public void getAllAddress() throws SQLException {
        System.out.println("Метод getAll");
        for (Address a : addressDAO.getAll()) {
            System.out.println(a);
        }
    }


    public void updateAddress(int id, String street, int house) throws SQLException {
        System.out.println("Метод update");
        Address a = addressDAO.get(id);
        a.setStreet(street);
        a.setHouse(house);
        addressDAO.update(a);
        System.out.println(a);
    }

    public void deleteAddress(int id) throws SQLException {
        System.out.println("Метод delete");
        Address a = addressDAO.get(id);
        addressDAO.delete(a);
    }


}
