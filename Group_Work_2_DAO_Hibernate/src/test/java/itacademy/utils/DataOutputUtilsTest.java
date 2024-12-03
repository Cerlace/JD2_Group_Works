package itacademy.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataOutputUtilsTest {
    private static final int MAX_SIZE_ROW = 10;
    @Test
    void shorterStringTest() {
        String expectedString = "Hello Wo..";
        String fullString = "Hello World!";
        String actualString = DataOutputUtils.getShortString(fullString, MAX_SIZE_ROW);
        Assertions.assertEquals(expectedString, actualString);
    }
}
