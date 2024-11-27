package itacademy.creators;

import itacademy.api.Creator;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.ConsoleUtils;

import java.io.Serializable;
import java.util.Scanner;

public class IdCreator implements Creator<Serializable> {
    private final Scanner scanner;

    public IdCreator(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Serializable create() throws InvalidInputException {
        System.out.print("Введите id: ");
        return ConsoleUtils.inputInt(scanner);
    }
}
