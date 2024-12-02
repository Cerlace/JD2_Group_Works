package itacademy.menu;

import itacademy.api.Command;
import itacademy.exceptions.checked.InvalidInputException;

import java.sql.SQLException;

public class MenuItem {
    private final String title;
    private final Command command;

    public MenuItem(String title, Command command) {
        this.title = title;
        this.command = command;
    }

    /**
     * Метод выполняет команду, привязанную к пункту меню
     * @throws SQLException при ошибке в работе с БД
     * @throws InvalidInputException при ошибке при вводе данных
     * @throws IllegalAccessException при ошибке в методах работы рефлексии
     */
    public void executeCommand() throws SQLException, InvalidInputException, IllegalAccessException {
        this.command.execute();
    }

    @Override
    public String toString() {
        return title;
    }
}
