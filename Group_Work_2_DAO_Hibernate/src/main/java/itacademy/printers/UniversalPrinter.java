package itacademy.printers;

import itacademy.api.Printer;
import itacademy.utils.DataOutputUtils;
import itacademy.utils.ReflectionUtils;

import java.util.List;

public abstract class UniversalPrinter<T> implements Printer<T> {
    private static final int COLUMN_WIDTH = 20;

    private static final String START_TABLE_ROW = "| ";
    private static final String END_TABLE_ROW = " |";
    private static final String COLUMN_SEPARATOR = " | ";
    private static final String HEADERS_SEPARATOR = " + ";
    private static final String LINE_ELEMENT = "-";
    private static final String NEW_LINE = "\n";

    private final Class<T> clazz;

    public UniversalPrinter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void printEntity(T entity) throws IllegalAccessException {
        this.printHeader();
        this.printOneEntity(entity);
        DataOutputUtils.displayMessage(this.getLine());
    }

    @Override
    public void printAllEntities(List<T> entities) throws IllegalAccessException {
        this.printHeader();
        for (T entity : entities) {
            this.printOneEntity(entity);
        }
        DataOutputUtils.displayMessage(this.getLine());
    }

    @Override
    public void printHeader() {
        List<String> headers = ReflectionUtils.getColumnsNames(this.clazz);

        String headerBuilder = this.getLine() +
                NEW_LINE +
                this.getTableRow(headers) +
                NEW_LINE +
                this.getLine();

        DataOutputUtils.displayMessage(headerBuilder);
    }

    private String getLine() {
        StringBuilder lineBuilder = new StringBuilder(START_TABLE_ROW);
        int columnsCount = ReflectionUtils.getColumnsNames(clazz).size();

        for (int i = 0; i < columnsCount - 1; i++) {
            lineBuilder.append(LINE_ELEMENT.repeat(COLUMN_WIDTH))
                    .append(HEADERS_SEPARATOR);
        }
        lineBuilder.append(LINE_ELEMENT.repeat(COLUMN_WIDTH))
                .append(END_TABLE_ROW);

        return lineBuilder.toString();
    }

    private String getTableRow(List<String> items) {
        int countItems = items.size();
        StringBuilder tableRowBuilder = new StringBuilder(START_TABLE_ROW);

        for (int i = 0; i < countItems; i++) {
            tableRowBuilder.append( DataOutputUtils.getShortString(
                    String.format("%-" + COLUMN_WIDTH + "s", items.get(i)), COLUMN_WIDTH));
            if (i < countItems - 1) {
                tableRowBuilder.append(COLUMN_SEPARATOR);
            } else {
                tableRowBuilder.append(END_TABLE_ROW);
            }
        }

        return tableRowBuilder.toString();
    }

    private void printOneEntity(T entity) throws IllegalAccessException {
        List<String> values = ReflectionUtils.getColumnsValues(entity);
        DataOutputUtils.displayMessage(this.getTableRow(values));
    }
}
