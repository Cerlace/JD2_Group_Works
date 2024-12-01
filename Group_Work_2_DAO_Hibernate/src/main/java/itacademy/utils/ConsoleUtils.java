package itacademy.utils;

import itacademy.exceptions.checked.InvalidInputException;

import java.util.Scanner;

public class ConsoleUtils {
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Метод закрывает сканер
     */
    public static void closeScanner() {
        SCANNER.close();
    }

    /**
     * Метод для ввода с консоли целого числа
     * @return число
     * @throws InvalidInputException если введено не целое число
     */
    public static int inputInt() throws InvalidInputException {
        if (SCANNER.hasNextInt()) {
            return SCANNER.nextInt();
        } else {
            SCANNER.next();
            throw new InvalidInputException("Вы ввели не целое число!");
        }
    }

    /**
     * Метод для ввода с консоли строки
     * @return введенная строка
     */
    public static String inputString() {
        return SCANNER.nextLine();
    }
}
