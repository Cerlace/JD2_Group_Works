package itacademy;

import itacademy.api.DAO;
import itacademy.dao.UniversalDAO;
import itacademy.dto.Address;
import itacademy.dto.People;

import java.sql.SQLException;

public class App {

    public static void main(String[] args) throws SQLException {
        //People
        DAO<People> peopleDAO = new UniversalDAO<>(People.class);
        //peopleDAO.createTable(); используй если нет таблицы

        People people1 = new People(1, "Анастасия", "Слуцкая", 24);
        People people2 = new People(2, "Дмитрий", "Джавицкий", 29);
        People people3 = new People(3, "Глеб", "Сальников", 32);
        People people4 = new People(4, "Екатерина", "Мухоморова", 21);
        People people5 = new People(5, "Илья", "Трубинский", 25);

        People people1Save = peopleDAO.save(people1);
        People people2Save = peopleDAO.save(people2);
        People people3Save = peopleDAO.save(people3);
        People people4Save = peopleDAO.save(people4);
        People people5Save = peopleDAO.save(people5);

        System.out.println("\n" + "Сохраняем данные в таблицу People");
        System.out.println("\t" + people1Save);
        System.out.println("\t" + people2Save);
        System.out.println("\t" + people3Save);
        System.out.println("\t" + people4Save);
        System.out.println("\t" + people5Save);

        System.out.println("\n" + "Читаем данные по id строки из таблицы People");
        People peopleGet3 = peopleDAO.get(people3.getId());
        System.out.println("\t" + peopleGet3);
        People peopleGet4 = peopleDAO.get(people4.getId());
        System.out.println("\t" + peopleGet4);

        System.out.println("\n" + "Читаем все данные из таблицы People");
        printPeopleDAO(peopleDAO);
        //System.out.println("\t" + peopleDAO.getAll()); неудобное отображение информации

        System.out.println("\n" + "Изменяем данные в таблице People");
        people3.setName("Виталий");
        people3.setSurname("Высокогорский");
        peopleDAO.update(people3);
        System.out.println("\t" + people3);

        System.out.println("\n" + "Удаляем строку " + people4.getId() + " из таблицы People");
        peopleDAO.delete(people4);

        System.out.println("\n" + "Повторно читаем все данные из таблицы People");
        printPeopleDAO(peopleDAO);


        //Address
        UniversalDAO<Address> addressDAO = new UniversalDAO<>(Address.class);
        //addressDAO.createTable(); используй если нет таблицы

        Address address1 = new Address(1, "Независимости", 1);
        Address address2 = new Address(2, "Тимирязева", 255);
        Address address3 = new Address(3, "Партизанская", 23);
        Address address4 = new Address(4, "Маяковского", 45);

        Address address1Save = addressDAO.save(address1);
        Address address2Save = addressDAO.save(address2);
        Address address3Save = addressDAO.save(address3);
        Address address4Save = addressDAO.save(address4);

        System.out.println("\n" + "Сохраняем данные в таблицу Address");
        System.out.println("\t" + address1Save);
        System.out.println("\t" + address2Save);
        System.out.println("\t" + address3Save);
        System.out.println("\t" + address4Save);

        System.out.println("\n" + "Читаем данные по id строки из таблицы Address");
        Address address4Get = addressDAO.get(address4.getId());
        System.out.println("\t" + address4Get);

        System.out.println("\n" + "Читаем все данные из таблицы Address");
        printAddressDAO(addressDAO);

        System.out.println("\n" + "Изменяем данные в таблице Address");
        address4.setStreet("Ванеева");
        address4.setHouse(55);
        addressDAO.update(address4);
        System.out.println("\t" + address4);

        System.out.println("\n" + "Удаляем строку " + address2.getId() + " из таблицы Address");
        addressDAO.delete(address2);

        System.out.println("\n" + "Повторно читаем все данные из таблицы Address");
        printAddressDAO(addressDAO);

    }

    private static void printPeopleDAO(DAO<People> peopleDAO) throws SQLException {
        for (int i = 0; i < peopleDAO.getAll().size(); i++) {
            System.out.println("\t" + peopleDAO.getAll().get(i));
        }
    }

    private static void printAddressDAO(DAO<Address> addressDAO) throws SQLException {
        for (int i = 0; i < addressDAO.getAll().size(); i++) {
            System.out.println("\t" + addressDAO.getAll().get(i));
        }
    }

}
