package itacademy.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataPrinterUtilsTest {
    @Test
    void shorterStringTest() {
        String expectedString = "Hello Wo..";
        String fullString = "Hello World!";
        String actualString = DataPrinterUtils.getShortString(fullString,10 );
        Assertions.assertEquals(expectedString, actualString);
    }
}
