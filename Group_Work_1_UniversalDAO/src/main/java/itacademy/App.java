package itacademy;

import itacademy.utils.UtilityShowInfo;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        UtilityShowInfo util = new UtilityShowInfo();

        util.createPeopleTable();
        util.savePeople(4, "Вася", "Пупкин", 40);
        util.savePeople(15, "Jhon", "Doe", 2);
        util.getPeople(16);
        util.getAllPeople();
        util.updatePeople(9, "Иван", "Рогозянский", 43);
        util.deletePeople(19);

        util.createAddressTable();
        util.saveAddress(1, "Downing st.", 5);
        util.getAddress(18);
        util.getAllAddress();
        util.updateAddress(16, "Красная", 25);
        util.deleteAddress(23);
    }
}
