package itacademy.creators;

import itacademy.api.Creator;
import itacademy.entity.People;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.ConsoleUtils;

import java.util.Scanner;

/**
 * Создает объект класса People на основе данных, введенных пользователем с консоли.
 * Использует утилиту ConsoleUtils для обработки ввода и валидации возраста.
 */
public class PeopleCreator implements Creator<People> {
    private final Scanner scanner;

    /**
     * Конструктор класса PeopleCreator. Инициализирует сканер для чтения данных с консоли, используя утилиту ConsoleUtils.
     */
    public PeopleCreator() {
        this.scanner = ConsoleUtils.getScanner();
    }

    /**
     * Создает объект People, запрашивая у пользователя имя, фамилию и возраст.
     *
     * @return Созданный объект People.
     * @throws InvalidInputException Если пользователь ввел некорректный возраст (не целое число).
     */
    @Override
    public People create() throws InvalidInputException {
        System.out.print("Введите имя: ");
        scanner.nextLine(); // Очищает буфер после предыдущего ввода (необходимо!)
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