package itacademy.utils;

import itacademy.api.DAO;
import itacademy.commands.address.*;
import itacademy.commands.people.*;
import itacademy.creators.AddressCreator;
import itacademy.dto.Address;
import itacademy.dto.People;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.menu.Menu;
import itacademy.menu.MenuItem;
import itacademy.creators.IdCreator;
import itacademy.creators.PeopleCreator;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuUtils {
    public static Menu getPeopleMenu(DAO<People> dao) {
        Scanner scanner = ConsoleUtils.getScanner();

        Menu menu = new Menu();
        menu.addItem(new MenuItem("Добавить человека в БД", new PeopleSaveCommand(dao, new PeopleCreator(scanner))));
        menu.addItem(new MenuItem("Изменить данные о человеке", new PeopleUpdateCommand(dao,
                new PeopleCreator(scanner),
                new IdCreator(scanner))));
        menu.addItem(new MenuItem("Удалить человека из БД", new PeopleDeleteCommand(dao,new IdCreator(scanner))));
        menu.addItem(new MenuItem("Найти человека", new PeopleGetCommand(dao,new IdCreator(scanner))));
        menu.addItem(new MenuItem("Показать всех людей в БД", new PeopleGetAllCommand(dao)));

        menu.setExitItem(new MenuItem("Выйти из программы", null));

        return menu;
    }

    public static Menu getAddressMenu(DAO<Address> dao) {
        Scanner scanner = ConsoleUtils.getScanner();

        Menu menu = new Menu();
        menu.addItem(new MenuItem("Добавить адрес в БД", new AddressSaveCommand(dao, new AddressCreator(scanner))));
        menu.addItem(new MenuItem("Изменить данные об адресе", new AddressUpdateCommand(dao,
                new AddressCreator(scanner),
                new IdCreator(scanner))));
        menu.addItem(new MenuItem("Удалить адрес из БД", new AddressDeleteCommand(dao,new IdCreator(scanner))));
        menu.addItem(new MenuItem("Найти адрес", new AddressGetCommand(dao,new IdCreator(scanner))));
        menu.addItem(new MenuItem("Показать все адреса в БД", new AddressGetAllCommand(dao)));

        menu.setExitItem(new MenuItem("Выйти из программы", null));

        return menu;
    }

    public static void runMenu(Menu menu) {
        Scanner scanner = ConsoleUtils.getScanner();
        boolean isExit = false;

        while (!isExit) {
            try {
                System.out.print(menu);
                int choice = ConsoleUtils.inputInt(scanner);
                if (choice == 0) {
                    isExit = true;
                } else {
                    MenuItem item = menu.getMenuItem(choice);
                    if (item != null) {
                        item.executeCommand();
                    } else {
                        System.out.println("Такого пункта меню нет!");
                    }
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            } catch (SQLException e) {
                System.out.println("Возникла ошибка при выполнении SQL запроса!\n" +
                        "Код ошибки: " + e.getSQLState() + "\n" +
                        e.getLocalizedMessage());
            }
        }
    }
}
