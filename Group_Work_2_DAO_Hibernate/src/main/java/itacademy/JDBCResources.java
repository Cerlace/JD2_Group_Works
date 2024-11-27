package itacademy;

import java.util.ResourceBundle;

public class JDBCResources {
    private final static String DATABASE_RESOURCE = "database";
    private final static  String URL_KEY = "url";
    private final static  String USER_KEY = "user";
    private final static  String PASSWORD_KEY = "password";

    private static final ResourceBundle resource = ResourceBundle.getBundle(DATABASE_RESOURCE);

    private static final String URL = getValue(URL_KEY);
    private static final String USER = getValue(USER_KEY);
    private static final String PASSWORD = getValue(PASSWORD_KEY);

    private static String getValue(String key) {
        return resource.getString(key);
    }

    public static String getURL() {
        return URL;
    }

    public static String getUser() {
        return USER;
    }

    public static String getPassword() {
        return PASSWORD;
    }
}
