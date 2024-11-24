package itacademy.utils;

import java.util.Scanner;

public class ConsoleUtils {

    public static int inputInt(Scanner console) {
        try {
            return Integer.parseInt(console.next());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Вы ввели не целое число!");
        }
    }
}
