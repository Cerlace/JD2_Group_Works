package itacademy.menu;

import itacademy.api.Command;

public class MenuItem {
    private final String title;
    private final Command command;

    public MenuItem(String title, Command command) {
        this.title = title;
        this.command = command;
    }

    public String getTitle() {
        return title;
    }

    public Command getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return title;
    }
}
