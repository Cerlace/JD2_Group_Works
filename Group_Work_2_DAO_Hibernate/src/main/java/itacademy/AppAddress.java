package itacademy;

import itacademy.api.DAO;
import itacademy.dao.impl.AddressDAOImpl;
import itacademy.dto.Address;
import itacademy.menu.Menu;
import itacademy.utils.ConsoleUtils;
import itacademy.utils.MenuUtils;

import java.sql.SQLException;

public class AppAddress {
    public static void main(String[] args) {
        DAO<Address> dao = new AddressDAOImpl();


        Menu addressMenu = MenuUtils.getAddressMenu(dao);
        MenuUtils.runMenu(addressMenu);
        ConsoleUtils.closeScanner();
    }
}
