package itacademy.creators;

import itacademy.api.Creator;
import itacademy.dto.People;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.ConsoleUtils;

import java.util.Scanner;

public class PeopleCreator implements Creator<People> {
    private final Scanner scanner;

    public PeopleCreator(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public People create() throws InvalidInputException {
        System.out.print("Введите имя: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Введите фамилию: ");
        String surname = scanner.nextLine();
        System.out.print("Введите возраст: ");
        int age = ConsoleUtils.inputInt(scanner);

        return People.builder()
                .name(name)
                .surname(surname)
                .age(age)
                .build();
    }
}
