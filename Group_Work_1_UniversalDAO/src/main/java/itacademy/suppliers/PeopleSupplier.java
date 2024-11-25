package itacademy.suppliers;

import itacademy.dto.People;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.ConsoleUtils;

import java.util.Scanner;
import java.util.function.Supplier;

public class PeopleSupplier implements Supplier<People> {
    private final Scanner scanner;

    public PeopleSupplier(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public People get() {
        try {
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
        } catch (InvalidInputException e) {
            throw new IllegalArgumentException(e);
        }

    }
}
