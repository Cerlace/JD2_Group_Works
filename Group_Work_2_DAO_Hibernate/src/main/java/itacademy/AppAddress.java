package itacademy;

import itacademy.menu.Menu;
import itacademy.utils.MenuUtils;

public class AppAddress {
    public static void main(String[] args) {
        Menu addressMenu = MenuUtils.getAddressMenu();
        MenuUtils.runMenu(addressMenu);
    }
}
