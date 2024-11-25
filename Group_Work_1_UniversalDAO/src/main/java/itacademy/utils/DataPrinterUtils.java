package itacademy.utils;

import itacademy.dto.Address;
import itacademy.dto.People;

import java.util.List;

/**
 * Класс DataPrinter отвечает за вывод информации об адресах и людях.
 *
 * <p>Этот класс предоставляет методы для вывода одной записи
 * или всех записей о {@link Address} и {@link People} в консоль.</p>
 */
public class DataPrinterUtils {

    /**
     * Выводит информацию об одном адресе.
     *
     * @param address объект адреса для отображения
     */
    public static void printOneAddress(Address address) {
        if (address != null) {
            printAddressHeader(); // Вывод заголовка таблицы адресов
            printAddressRow(address); // Вывод строки с данными об адресе
        } else {
            System.out.println("Адрес не предоставлен."); // Сообщение при отсутствии данных
        }
        System.out.println(); // Пустая строка для отделения выводов
    }

    /**
     * Выводит информацию о всех адресах.
     *
     * @param addresses список адресов для отображения
     */
    public static void printAllAddresses(List<Address> addresses) {
        if (addresses != null && !addresses.isEmpty()) {
            printAddressHeader(); // Вывод заголовка таблицы адресов
            for (Address address : addresses) {
                printAddressRow(address); // Вывод строки для каждого адреса
            }
        } else {
            System.out.println("Нет адресов для вывода."); // Сообщение при отсутствии адресов
        }
        System.out.println(); // Пустая строка для отделения выводов
    }

    /**
     * Выводит информацию о человеке.
     *
     * @param people объект человека для отображения
     */
    public static void printOnePeople(People people) {
        if (people != null) {
            printPeopleHeader(); // Вывод заголовка таблицы людей
            printPeopleRow(people); // Вывод строки с данными о человеке
        } else {
            System.out.println("Данные о человеке не предоставлены."); // Сообщение при отсутствии данных
        }
        System.out.println(); // Пустая строка для отделения выводов
    }

    /**
     * Выводит информацию о всех людях.
     *
     * @param peopleList список людей для отображения
     */
    public static void printAllPeople(List<People> peopleList) {
        if (peopleList != null && !peopleList.isEmpty()) {
            printPeopleHeader(); // Вывод заголовка таблицы людей
            for (People people : peopleList) {
                printPeopleRow(people); // Вывод строки для каждого человека
            }
        } else {
            System.out.println("Нет людей для вывода."); // Сообщение при отсутствии людей
        }
        System.out.println(); // Пустая строка для отделения выводов
    }

    /**
     * Выводит заголовок таблицы адресов.
     */
    private static void printAddressHeader() {
        System.out.printf("+----+--------------------+-------+%n");
        System.out.printf("| ID | %-18s | %-5s |%n", "Улица", "Дом"); // Заголовки "Улица" и "Дом"
        System.out.printf("+----+--------------------+-------+%n");
    }

    /**
     * Выводит строку с данными об адресе.
     *
     * @param address объект адреса для отображения
     */
    private static void printAddressRow(Address address) {
        System.out.printf("| %-2d | %-18s | %-5s |%n",
                address.getId(), // ID адреса
                address.getStreet(), // Улица
                address.getHouse()); // Дом
        System.out.printf("+----+--------------------+-------+%n"); // Завершение строки таблицы
    }

    /**
     * Выводит заголовок таблицы людей.
     */
    private static void printPeopleHeader() {
        System.out.printf("+----+--------------------+--------------------+----------+%n");
        System.out.printf("| ID | %-18s | %-18s | %-8s |%n", "Имя", "Фамилия", "Возраст"); // Заголовки "Имя", "Фамилия" и "Возраст"
        System.out.printf("+----+--------------------+--------------------+----------+%n");
    }

    /**
     * Выводит строку с данными о человеке.
     *
     * @param people объект человека для отображения
     */
    private static void printPeopleRow(People people) {
        System.out.printf("| %-2d | %-18s | %-18s | %-8d |%n",
                people.getId(), // ID человека
                people.getName(), // Имя
                people.getSurname(), // Фамилия
                people.getAge()); // Возраст
        System.out.printf("+----+--------------------+--------------------+----------+%n"); // Завершение строки таблицы
    }

    private String shortenString(String str, int maxlength) {
        if (str.length() > maxlength) {
            str = str.substring(0, maxlength - 2) + "..";

        }
        return str;
    }
}