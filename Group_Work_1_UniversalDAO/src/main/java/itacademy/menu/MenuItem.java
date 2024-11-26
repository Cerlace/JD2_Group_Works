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

    public void executeCommand() throws SQLException, InvalidInputException {
        this.command.execute();
    }

    @Override
    public String toString() {
        return title;
    }
}
