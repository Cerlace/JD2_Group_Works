package itacademy.printers;

import itacademy.api.Printer;
import itacademy.dto.Address;
import itacademy.utils.DataPrinterUtils;

import java.util.List;

public class AddressPrinter implements Printer<Address> {
    private final static int ID_COLUMN_WIDTH = 10;
    private final static int STREET_COLUMN_WIDTH = 30;
    private final static int HOUSE_COLUMN_WIDTH = 10;

    /**
     * Выводит информацию об одном адресе.
     *
     * @param entity объект адреса для отображения
     */
    @Override
    public void printEntity(Address entity) {
        if (entity != null) {
            this.printHeader(); // Вывод заголовка таблицы адресов
            this.printAddress(entity); // Вывод строки с данными об адресе
            this.printLine();
        } else {
            System.out.println("Адрес не предоставлен."); // Сообщение при отсутствии данных
        }
        System.out.println(); // Пустая строка для отделения выводов
    }

    /**
     * Выводит информацию о всех адресах.
     *
     * @param entities список адресов для отображения
     */
    @Override
    public void printAllEntities(List<Address> entities) {
        if (entities != null && !entities.isEmpty()) {
            this.printHeader(); // Вывод заголовка таблицы адресов
            for (Address address : entities) {
                this.printAddress(address); // Вывод строки для каждого адреса
            }
            this.printLine();
        } else {
            System.out.println("Нет адресов для вывода."); // Сообщение при отсутствии адресов
        }
        System.out.println(); // Пустая строка для отделения выводов
    }

    /**
     * Выводит заголовок таблицы адресов.
     */
    @Override
    public void printHeader() {
        this.printLine();
        this.printTableRow("ID", "Улица", "Дом");
        this.printLine();
    }

    /**
     * Выводит строку с данными об адресе.
     *
     * @param address объект адреса для отображения
     */
    private void printAddress(Address address) {
        this.printTableRow(
                String.valueOf(address.getId()),
                DataPrinterUtils.getShortString(address.getStreet(),STREET_COLUMN_WIDTH),
                String.valueOf(address.getHouse())
        );
    }

    private void printTableRow(String id, String street, String house) {
        String row = String.format("| %-" + ID_COLUMN_WIDTH + "s |"
                        + " %-" + STREET_COLUMN_WIDTH + "s |"
                        + " %-" + HOUSE_COLUMN_WIDTH + "s |",
                id, street, house);

        System.out.println(row);
    }

    private void printLine() {
        String line = "| " + "-".repeat(ID_COLUMN_WIDTH) + " + "
                + "-".repeat(STREET_COLUMN_WIDTH) + " + "
                + "-".repeat(HOUSE_COLUMN_WIDTH) + " |";

        System.out.println(line);
    }
}
