package itacademy.creators;

import itacademy.api.Creator;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.ConsoleUtils;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Создает объект Serializable, представляющий собой целое число, введенное пользователем.
 * Использует утилиту ConsoleUtils для получения и обработки ввода с консоли.
 */
public class IdCreator implements Creator<Serializable> {
    private final Scanner scanner;

    /**
     * Конструктор класса IdCreator. Инициализирует сканер для чтения данных с консоли, используя утилиту ConsoleUtils.
     */
    public IdCreator() {
        this.scanner = ConsoleUtils.getScanner();
    }

    /**
     * Создает объект Serializable (Integer), запрашивая целое число у пользователя.
     *
     * @return Целое число, введенное пользователем, упакованное в объект Integer.
     * @throws InvalidInputException Если пользователь ввел не целое число.
     */
    @Override
    public Serializable create() throws InvalidInputException {
        System.out.print("Введите id: ");
        return ConsoleUtils.inputInt(scanner);
    }
}