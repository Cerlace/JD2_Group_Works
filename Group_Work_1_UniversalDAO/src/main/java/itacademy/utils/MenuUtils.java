package itacademy.utils;

import itacademy.api.PeopleDAO;
import itacademy.commands_dao.people.*;
import itacademy.dao.impl.PeopleDAOImpl;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.menu.Menu;
import itacademy.menu.MenuItem;

import java.sql.SQLException;

public class MenuUtils {

    public static void runMenu(Menu menu) {
        while (true) {
            try {
                System.out.print(menu);

                int choice = ConsoleUtils.inputInt();
                if (choice == 0) {
                    break;
                }
                menu.executeCommand(choice);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            } catch (SQLException e) {
                System.out.println("Возникла ошибка при выполнении SQL запроса!\n" +
                        "Код ошибки: " + e.getSQLState() + "\n" +
                        e.getLocalizedMessage());
            }
        }
    }

    public static Menu getPeopleMenu() {
        PeopleDAO peopleDAO = new PeopleDAOImpl();
        Menu menu = new Menu();
        menu.addItem(1, new MenuItem("Добавить человека в БД", new PeopleSaveCommand(peopleDAO)));
        menu.addItem(2, new MenuItem("Изменить данные о человеке", new PeopleUpdateCommand(peopleDAO)));
        menu.addItem(3, new MenuItem("Удалить человека из БД", new PeopleDeleteCommand(peopleDAO)));
        menu.addItem(4, new MenuItem("Найти человека", new PeopleGetCommand(peopleDAO)));
        menu.addItem(5, new MenuItem("Показать всех людей в БД", new PeopleGetAllCommand(peopleDAO)));
        menu.addItem(0, new MenuItem("Выйти из программы", null));

        return menu;
    }
}
