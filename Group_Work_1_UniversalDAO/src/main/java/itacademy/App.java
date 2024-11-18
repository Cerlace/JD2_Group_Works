package itacademy;

import itacademy.api.DAO;
import itacademy.dao.UniversalDAO;
import itacademy.dto.Address;
import itacademy.dto.People;
import itacademy.utils.UtilityShowInfo;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        DAO<People> peopleDAO = new UniversalDAO<>(People.class);
        peopleDAO.createTable();//созданная таблица

        UtilityShowInfo util = new UtilityShowInfo();
       util.savePeople(4, "Вася", "Пупкин", 40);
       util.savePeople(15, "Jhon", "Doe", 2);
       util.getPeople(16);
       util.getAllPeople();
       util.updatePeople(9, "Иван", "Рогозянский", 43);
       util.deletePeople(19);

        UniversalDAO<Address> addressDAO = new UniversalDAO<>(Address.class);
        addressDAO.createTable();//созданная таблица

        util.saveAddress(1, "Downing st.", 5);
        util.getAddress(18);
        util.getAllAddress();
        util.updateAddress(16, "Красная", 25);
        util.deleteAddress(23);



    }
}
