package itacademy.menu;

import java.util.*;

public class Menu {
    private final List<MenuItem> items = new ArrayList<>();
    private MenuItem exit = null;

    private Map<Integer, MenuItem> menuItems = new TreeMap<>();
    private Integer currentId = 1;

    public void addItem(MenuItem item) {
        this.menuItems.put(currentId++, item);
    }

    public MenuItem getMenuItem(int id) {
        return this.menuItems.get(id);
    }

    public void setExitItem(MenuItem item) {
        menuItems.put(0, item);
    }

    @Override
    public String toString() {
        StringBuilder menuBuilder = new StringBuilder();

        for (Map.Entry<Integer, MenuItem> item : menuItems.entrySet()) {
            if (item.getKey() != 0) {
                menuBuilder.append(item.getKey())
                        .append(". ")
                        .append(item.getValue())
                        .append("\n");
            } else {
                exit = item.getValue();
            }
        }

        if (exit != null) {
            menuBuilder.append("0. ")
                    .append(exit)
                    .append("\n");
        }
        menuBuilder.append("Введите номер нужного пункта: ");

        return menuBuilder.toString();
    }
}
