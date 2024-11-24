package itacademy.commands_console;

import itacademy.api.CommandConsole;
import itacademy.dto.People;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.ConsoleUtils;

import java.util.Scanner;

public class PeopleInputCommand implements CommandConsole<People> {
    private final Scanner scanner;

    public PeopleInputCommand(final Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public People execute() throws InvalidInputException {
        System.out.print("Введите имя: ");
        this.scanner.nextLine();
        String name = this.scanner.nextLine();
        System.out.print("Введите фамилию: ");
        String surname = this.scanner.nextLine();
        System.out.print("Введите возраст: ");
        int age = ConsoleUtils.inputInt(this.scanner);

        return People.builder()
                .name(name)
                .surname(surname)
                .age(age)
                .build();
    }
}
