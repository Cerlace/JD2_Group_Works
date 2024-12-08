package itacademy.utils;

import itacademy.api.AddressDAO;
import itacademy.api.PeopleDAO;
import itacademy.commands.ExitCommand;
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
import itacademy.dao.impl.AddressDAOImpl;
import itacademy.dao.impl.PeopleDAOImpl;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.menu.Menu;
import itacademy.menu.MenuItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuUtils.class);

    /**
     * Метод создает и возвращает меню для работы с entity People
     * @return меню
     */
    public static Menu getPeopleMenu() {
        PeopleDAO dao = new PeopleDAOImpl();
        Menu menu = new Menu();
        menu.addItem(new MenuItem("Добавить человека в БД", new PeopleSaveCommand(dao)));
        menu.addItem(new MenuItem("Изменить данные о человеке", new PeopleUpdateCommand(dao)));
        menu.addItem(new MenuItem("Удалить человека из БД", new PeopleDeleteCommand(dao)));
        menu.addItem(new MenuItem("Найти человека", new PeopleGetCommand(dao)));
        menu.addItem(new MenuItem("Показать всех людей в БД", new PeopleGetAllCommand(dao)));

        menu.setExitItem(new MenuItem("Выйти из программы", new ExitCommand<>(dao)));

        return menu;
    }

    /**
     * Метод создает и возвращает меню для работы с entity Address
     * @return меню
     */
    public static Menu getAddressMenu() {
        AddressDAO dao = new AddressDAOImpl();
        Menu menu = new Menu();
        menu.addItem(new MenuItem("Добавить адрес в БД", new AddressSaveCommand(dao)));
        menu.addItem(new MenuItem("Изменить данные об адресе", new AddressUpdateCommand(dao)));
        menu.addItem(new MenuItem("Удалить адрес из БД", new AddressDeleteCommand(dao)));
        menu.addItem(new MenuItem("Найти адрес", new AddressGetCommand(dao)));
        menu.addItem(new MenuItem("Показать все адреса в БД", new AddressGetAllCommand(dao)));

        menu.setExitItem(new MenuItem("Выйти из программы", new ExitCommand<>(dao)));

        return menu;
    }

    /**
     * Метод для обработки меню и выполнения команд его пунктов
     * @param menu меню
     */
    public static void runMenu(Menu menu) {
        boolean isExit = false;

        while (!isExit) {
            try {
                LOGGER.debug(menu.toString());
                int choice = ConsoleUtils.inputInt();
                if (choice == 0) {
                    isExit = true;
                }
                MenuItem item = menu.getMenuItem(choice);
                if (item != null) {
                    item.executeCommand();
                } else {
                    LOGGER.debug("Такого пункта меню нет!");
                }
            } catch (InvalidInputException | IllegalAccessException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }
}
