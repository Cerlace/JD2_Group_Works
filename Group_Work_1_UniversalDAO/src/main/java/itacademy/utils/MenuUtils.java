package itacademy.utils;

import itacademy.menu.Menu;
import itacademy.menu.MenuItem;

public class MenuUtils {

    public static Menu getPeopleMenu() {
        Menu menu = new Menu();
        menu.addItem(new MenuItem("Добавить человека в БД", 1));
        menu.addItem(new MenuItem("Изменить данные о человеке", 2));
        menu.addItem(new MenuItem("Удалить человека из БД", 3));
        menu.addItem(new MenuItem("Найти человека", 4));
        menu.addItem(new MenuItem("Показать всех людей в БД", 5));
        menu.addItem(new MenuItem("Выйти из программы", 0));

        return menu;
    }
}
