package itacademy.commands_console;

import itacademy.api.CommandConsole;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.ConsoleUtils;

import java.io.Serializable;
import java.util.Scanner;

public class IdInputCommand implements CommandConsole<Serializable> {
    private final Scanner scanner;

    public IdInputCommand(Scanner scanner) {
        this.scanner = scanner;
    }
    @Override
    public Serializable execute() throws InvalidInputException {
        System.out.print("Введите id: ");
        return ConsoleUtils.inputInt(this.scanner);
    }
}
