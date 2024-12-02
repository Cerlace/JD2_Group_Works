package itacademy.utils;

import itacademy.commands.address.AddressDeleteCommand;
import itacademy.commands.address.AddressGetAllCommand;
import itacademy.commands.address.AddressGetCommand;
import itacademy.commands.address.AddressSaveCommand;
import itacademy.commands.address.AddressUpdateCommand;
import itacademy.commands.people.PeopleDeleteCommand;
import itacademy.commands.people.PeopleGetAllCommand;
import itacademy.commands.people.PeopleSaveCommand;
import itacademy.commands.people.PeopleUpdateCommand;
import itacademy.commands.people.PeopleGetCommand;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.menu.Menu;
import itacademy.menu.MenuItem;

import java.sql.SQLException;

public class MenuUtils {
    public static Menu getPeopleMenu() {
        Menu menu = new Menu();
        menu.addItem(new MenuItem("Добавить человека в БД", new PeopleSaveCommand()));
        menu.addItem(new MenuItem("Изменить данные о человеке", new PeopleUpdateCommand()));
        menu.addItem(new MenuItem("Удалить человека из БД", new PeopleDeleteCommand()));
        menu.addItem(new MenuItem("Найти человека", new PeopleGetCommand()));
        menu.addItem(new MenuItem("Показать всех людей в БД", new PeopleGetAllCommand()));

        menu.setExitItem(new MenuItem("Выйти из программы", null));

        return menu;
    }

    public static Menu getAddressMenu() {
        Menu menu = new Menu();
        menu.addItem(new MenuItem("Добавить адрес в БД", new AddressSaveCommand()));
        menu.addItem(new MenuItem("Изменить данные об адресе", new AddressUpdateCommand()));
        menu.addItem(new MenuItem("Удалить адрес из БД", new AddressDeleteCommand()));
        menu.addItem(new MenuItem("Найти адрес", new AddressGetCommand()));
        menu.addItem(new MenuItem("Показать все адреса в БД", new AddressGetAllCommand()));

        menu.setExitItem(new MenuItem("Выйти из программы", null));

        return menu;
    }

    public static void runMenu(Menu menu) {
        boolean isExit = false;

        while (!isExit) {
            try {
                System.out.print(menu);
                int choice = ConsoleUtils.inputInt();
                if (choice == 0) {
                    isExit = true;
                    HibernateUtils.close();
                    ConsoleUtils.closeScanner();
                } else {
                    MenuItem item = menu.getMenuItem(choice);
                    if (item != null) {
                        item.executeCommand();
                    } else {
                        System.out.println("Такого пункта меню нет!");
                    }
                }
            } catch (InvalidInputException | IllegalAccessException e) {
                System.out.println(e.getMessage());
            } catch (SQLException e) {
                System.out.println("Возникла ошибка при выполнении SQL запроса!\n" +
                        "Код ошибки: " + e.getSQLState() + "\n" +
                        e.getLocalizedMessage());
            }
        }
    }
}
