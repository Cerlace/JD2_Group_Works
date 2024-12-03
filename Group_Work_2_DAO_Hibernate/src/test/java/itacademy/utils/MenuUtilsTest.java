package itacademy.utils;


import itacademy.menu.Menu;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MenuUtilsTest {
    @Test
    public void getPeopleMenuTest() {
        Menu menu = MenuUtils.getPeopleMenu();
        assertNotNull(menu);
    }

    @Test
    public void getAddressMenuTest() {
        Menu menu = MenuUtils.getAddressMenu();
        assertNotNull(menu);
    }
}
