package itacademy.utils;

public class DataOutputUtils {
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
