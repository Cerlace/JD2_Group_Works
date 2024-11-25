package itacademy.utils;

import itacademy.exceptions.checked.InvalidInputException;

import java.util.Scanner;

public class ConsoleUtils {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static Scanner getScanner() {
        return SCANNER;
    }

    public static void closeScanner() {
        SCANNER.close();
    }

    public static int inputInt(Scanner console) throws InvalidInputException {
        if (console.hasNextInt()) {
            return console.nextInt();
        } else {
            console.next();
            throw new InvalidInputException("Вы ввели не целое число!");
        }
    }
}
