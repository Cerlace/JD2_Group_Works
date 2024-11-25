package itacademy.utils;

import itacademy.dto.People;
import itacademy.exceptions.checked.InvalidInputException;

import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;
import java.util.Scanner;

public class ConsoleUtils {
    private static final Scanner console = new Scanner(System.in);

    public static Serializable inputId() throws InvalidInputException {
        System.out.print("Введите id: ");
        return inputInt();
    }
    public static People inputPeopleData() throws InvalidInputException {
        System.out.print("Введите имя: ");
        console.nextLine();
        String name = console.nextLine();
        System.out.print("Введите фамилию: ");
        String surname = console.nextLine();
        System.out.print("Введите возраст: ");
        int age = inputInt();

        return People.builder()
                .name(name)
                .surname(surname)
                .age(age)
                .build();
    }

    public static int inputInt() throws InvalidInputException {
        if (console.hasNextInt()) {
            return console.nextInt();
        } else {
            console.next();
            throw new InvalidInputException("Вы ввели не целое число!");
        }
    }


}
