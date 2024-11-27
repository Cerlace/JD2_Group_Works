package itacademy.creators;

import itacademy.api.Creator;
import itacademy.dto.Address;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.ConsoleUtils;

import java.util.Scanner;

public class AddressCreator implements Creator<Address> {
    private final Scanner scanner;

    public AddressCreator(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Address create() throws InvalidInputException {
        System.out.print("Введите название улицы: ");
        scanner.nextLine();
        String street = scanner.nextLine();
        System.out.print("Введите номер дома: ");
        int house = ConsoleUtils.inputInt(scanner);

        return Address.builder()
                .street(street)
                .house(house)
                .build();
    }
}
