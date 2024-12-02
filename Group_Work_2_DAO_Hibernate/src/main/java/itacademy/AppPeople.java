package itacademy;

import itacademy.menu.Menu;
import itacademy.utils.MenuUtils;

public class AppPeople {
    public static void main(String[] args) {
        Menu peopleMenu = MenuUtils.getPeopleMenu();
        MenuUtils.runMenu(peopleMenu);
    }
}
