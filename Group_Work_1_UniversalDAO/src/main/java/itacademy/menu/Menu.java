package itacademy.menu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Menu {
    private final List<MenuItem> items = new ArrayList<>();
    private MenuItem exit = null;

    public void addItem(MenuItem item) {
        this.items.add(item);
    }

    @Override
    public String toString() {
        StringBuilder menuBuilder = new StringBuilder();

        items.sort(Comparator.comparingInt(MenuItem::getId));


        for (MenuItem item : items) {
            if (item.getId() != 0) {
                menuBuilder.append(item).append("\n");
            } else {
                exit = item;
            }
        }

        if (exit != null) {
            menuBuilder.append(exit).append("\n");
        }
        menuBuilder.append("Введите номер нужного пункта: ");

        return menuBuilder.toString();
    }
}
