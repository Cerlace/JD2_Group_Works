package itacademy.creators;

import itacademy.api.Creator;
import itacademy.entity.Address;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.ConsoleUtils;

import java.util.Scanner;

/**
 * Создает объект Address на основе данных, введенных пользователем с консоли.
 * Использует утилиту ConsoleUtils для получения и обработки ввода.
 */
public class AddressCreator implements Creator<Address> {
    private final Scanner scanner;

    /**
     * Конструктор класса AddressCreator. Инициализирует сканер для чтения данных с консоли.
     */
    public AddressCreator() {
        this.scanner = ConsoleUtils.getScanner();
    }

    /**
     * Создает объект Address, запрашивая у пользователя данные с консоли.
     *
     * @return Созданный объект Address.
     * @throws InvalidInputException Если пользователь ввел некорректные данные (не целое число для номера дома например).
     */
    @Override
    public Address create() throws InvalidInputException {
        System.out.print("Введите название улицы: ");
        scanner.nextLine(); // Удаляет остаток предыдущего ввода, если он есть
        String street = scanner.nextLine();
        System.out.print("Введите номер дома: ");
        int house = ConsoleUtils.inputInt(scanner);

        return Address.builder()
                .street(street)
                .house(house)
                .build();
    }
}