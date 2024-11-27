package itacademy;

import itacademy.api.DAO;
import itacademy.dao.impl.PeopleDAOImpl;
import itacademy.dto.People;
import itacademy.menu.Menu;
import itacademy.utils.ConsoleUtils;
import itacademy.utils.MenuUtils;

import java.sql.SQLException;

public class AppPeople {
    public static void main(String[] args) {
        DAO<People> dao = new PeopleDAOImpl();

        try {
            dao.createTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Menu peopleMenu = MenuUtils.getPeopleMenu(dao);
        MenuUtils.runMenu(peopleMenu);
        ConsoleUtils.closeScanner();
    }
}
