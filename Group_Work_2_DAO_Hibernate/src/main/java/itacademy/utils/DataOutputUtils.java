package itacademy.utils;

public class DataOutputUtils {
    /**
     * Метод для вывода сообщения в консоль
     * @param message сообщение
     */
    public static void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Метод обрезает строку, если она длиннее ширины столбца таблицы
     * @param str строка, которая проверяется
     * @param maxlength ширина столбца
     * @return обрезанная строка
     */
    public static String getShortString(String str, int maxlength) {
        if (str.length() > maxlength) {
            str = str.substring(0, maxlength - 2) + "..";

        }
        return str;
    }
}
