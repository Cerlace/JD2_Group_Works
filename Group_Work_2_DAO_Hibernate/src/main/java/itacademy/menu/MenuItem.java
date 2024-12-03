package itacademy.menu;

import itacademy.api.Command;
import itacademy.exceptions.checked.InvalidInputException;

public class MenuItem {
    private final String title;
    private final Command command;

    public MenuItem(String title, Command command) {
        this.title = title;
        this.command = command;
    }

    /**
     * Метод выполняет команду, привязанную к пункту меню
     * @throws InvalidInputException при ошибке при вводе данных
     * @throws IllegalAccessException при ошибке в методах работы рефлексии
     */
    public void executeCommand() throws InvalidInputException, IllegalAccessException {
        this.command.execute();
    }

    @Override
    public String toString() {
        return title;
    }
}
