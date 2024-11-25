package itacademy.menu;

import itacademy.api.Command;
import itacademy.exceptions.checked.InvalidInputException;

import java.sql.SQLException;
import java.util.*;

public class Menu {
    private final Map<Integer, MenuItem> items = new HashMap<>();

    public void addItem(int id, MenuItem item) {
        this.items.put(id, item);
    }

    public void executeCommand(int commandId) throws InvalidInputException, SQLException {
        if (items.containsKey(commandId)) {
            Command command = items.get(commandId).getCommand();
            if (command != null) {
                command.execute();
            }
        } else {
            System.out.println("Такого пункта меню не существует!");
        }
    }

    @Override
    public String toString() {
        StringBuilder menuBuilder = new StringBuilder();

        for (int i = 1; i < items.size(); i++) {
            menuBuilder
                    .append(i)
                    .append(". ")
                    .append(items.get(i))
                    .append("\n");
        }
        if (items.containsKey(0)) {
            menuBuilder
                    .append("0. ")
                    .append(items.get(0))
                    .append("\n");
        }
        menuBuilder.append("Введите номер нужного пункта: ");

        return menuBuilder.toString();
    }
}
