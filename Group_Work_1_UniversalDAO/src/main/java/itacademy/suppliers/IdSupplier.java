package itacademy.suppliers;

import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.ConsoleUtils;

import java.io.Serializable;
import java.util.Scanner;
import java.util.function.Supplier;

public class IdSupplier implements Supplier<Serializable> {
    private final Scanner scanner;

    public IdSupplier(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Serializable get() {
        try {
            System.out.print("Введите id: ");
            return ConsoleUtils.inputInt(scanner);
        } catch (InvalidInputException e) {
            throw new RuntimeException(e);
        }
    }
}
