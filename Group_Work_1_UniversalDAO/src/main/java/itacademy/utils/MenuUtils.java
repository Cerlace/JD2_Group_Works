package itacademy.utils;

import itacademy.api.DAO;
import itacademy.commands.people.*;
import itacademy.dto.People;
import itacademy.menu.Menu;
import itacademy.menu.MenuItem;
import itacademy.suppliers.IdSupplier;
import itacademy.suppliers.PeopleSupplier;

import java.util.Scanner;

public class MenuUtils {
    public static Menu getPeopleMenu(DAO<People> dao) {
        Scanner scanner = ConsoleUtils.getScanner();

        Menu menu = new Menu();
        menu.addItem(new MenuItem("Добавить человека в БД", new PeopleSaveCommand(dao, new PeopleSupplier(scanner))));
        menu.addItem(new MenuItem("Изменить данные о человеке", new PeopleUpdateCommand(dao,
                new PeopleSupplier(scanner),
                new IdSupplier(scanner))));
        menu.addItem(new MenuItem("Удалить человека из БД", new PeopleDeleteCommand(dao,new IdSupplier(scanner))));
        menu.addItem(new MenuItem("Найти человека", new PeopleGetCommand(dao,new IdSupplier(scanner))));
        menu.addItem(new MenuItem("Показать всех людей в БД", new PeopleGetAllCommand(dao)));

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
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
