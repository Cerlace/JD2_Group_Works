package itacademy.menu;


import java.util.Map;
import java.util.TreeMap;

public class Menu {
    private static final int EXIT_ITEM_ID = 0;
    private MenuItem exit = null;

    private final Map<Integer, MenuItem> menuItems = new TreeMap<>();
    private Integer currentId = 1;

    /**
     * Метод добавляет в меню пункт
     * @param item пункт меню
     */
    public void addItem(MenuItem item) {
        this.menuItems.put(currentId++, item);
    }

    /**
     * Метод получает пункт из меню по его id
     * @param id номер пункта меню
     * @return пункт меню
     */
    public MenuItem getMenuItem(int id) {
        return this.menuItems.get(id);
    }

    /**
     * Метод устанавливает пункт меню для выхода из приложения
     * @param item пункт меню
     */
    public void setExitItem(MenuItem item) {
        menuItems.put(EXIT_ITEM_ID, item);
    }

    @Override
    public String toString() {
        StringBuilder menuBuilder = new StringBuilder();

        for (Map.Entry<Integer, MenuItem> item : menuItems.entrySet()) {
            if (item.getKey() != EXIT_ITEM_ID) {
                this.addMenuRow(menuBuilder, item.getKey(), item.getValue());
            } else {
                exit = item.getValue();
            }
        }

        if (exit != null) {
            this.addMenuRow(menuBuilder, EXIT_ITEM_ID, exit);
        }
        menuBuilder.append("Введите номер нужного пункта: ");

        return menuBuilder.toString();
    }

    private void addMenuRow(StringBuilder stringBuilder,
                              Integer id, MenuItem item) {
        stringBuilder.append(id)
                .append(". ")
                .append(item)
                .append("\n");
    }
}
