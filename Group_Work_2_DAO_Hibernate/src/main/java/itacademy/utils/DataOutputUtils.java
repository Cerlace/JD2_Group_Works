package itacademy.utils;

public class DataOutputUtils {
    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public static String getShortString(String str, int maxlength) {
        if (str.length() > maxlength) {
            str = str.substring(0, maxlength - 2) + "..";

        }
        return str;
    }
}
