package itacademy;

import itacademy.menu.Menu;
import itacademy.utils.MenuUtils;

public class App {
    public static void main(String[] args) {
        Menu peopleMenu = MenuUtils.getPeopleMenu();

        MenuUtils.runMenu(peopleMenu);
    }
}
