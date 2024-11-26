package itacademy.printers;

import itacademy.api.Printer;
import itacademy.dto.People;
import itacademy.utils.DataPrinterUtils;

import java.util.List;

public class PeoplePrinter implements Printer<People> {
    private final static int ID_COLUMN_WIDTH = 10;
    private final static int NAME_COLUMN_WIDTH = 20;
    private final static int SURNAME_COLUMN_WIDTH = 20;
    private final static int AGE_COLUMN_WIDTH = 10;

    /**
     * Выводит информацию о человеке.
     *
     * @param entity объект человека для отображения
     */
    @Override
    public void printEntity(People entity) {
        if (entity != null) {
            this.printHeader(); // Вывод заголовка таблицы людей
            printPeople(entity); // Вывод строки с данными о человеке
            this.printLine();
        } else {
            System.out.println("Данные о человеке не предоставлены."); // Сообщение при отсутствии данных
        }
        System.out.println(); // Пустая строка для отделения выводов
    }

    /**
     * Выводит информацию о всех людях.
     *
     * @param entities список людей для отображения
     */
    @Override
    public void printAllEntities(List<People> entities) {
        if (entities != null && !entities.isEmpty()) {
            this.printHeader(); // Вывод заголовка таблицы людей
            for (People people : entities) {
                printPeople(people); // Вывод строки для каждого человека
            }
            this.printLine();
        } else {
            System.out.println("Нет людей для вывода."); // Сообщение при отсутствии людей
        }
        System.out.println(); // Пустая строка для отделения выводов
    }

    /**
     * Выводит заголовок таблицы людей.
     */
    @Override
    public void printHeader() {
        printLine();
        printTableRow("ID","Имя", "Фамилия", "Возраст");
        printLine();
    }

    /**
     * Выводит строку с данными о человеке.
     *
     * @param people объект человека для отображения
     */
    private void printPeople(People people) {
        printTableRow(
                String.valueOf(people.getId()),
                DataPrinterUtils.getShortString(people.getName(), NAME_COLUMN_WIDTH),
                DataPrinterUtils.getShortString(people.getSurname(), SURNAME_COLUMN_WIDTH),
                String.valueOf(people.getAge())
        );
    }

    private void printTableRow(String id, String name, String surname, String age) {
        String row = String.format("| %-" + ID_COLUMN_WIDTH + "s |"
        + " %-" + NAME_COLUMN_WIDTH + "s |"
        + " %-" + SURNAME_COLUMN_WIDTH + "s |"
        + " %-" + AGE_COLUMN_WIDTH + "s |",
                id, name, surname, age);

        System.out.println(row);
    }

    private void printLine() {
        String line = "| " + "-".repeat(ID_COLUMN_WIDTH) + " + "
                + "-".repeat(NAME_COLUMN_WIDTH) + " + "
                + "-".repeat(SURNAME_COLUMN_WIDTH) + " + "
                + "-".repeat(AGE_COLUMN_WIDTH) + " |";

        System.out.println(line);
    }
}
