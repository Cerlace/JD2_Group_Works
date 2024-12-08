package itacademy.printers.impl;

import itacademy.api.Printer;
import itacademy.entity.People;
import itacademy.printers.UniversalPrinter;
import org.slf4j.Logger;

public class PeoplePrinter extends UniversalPrinter<People> implements Printer<People> {

    public PeoplePrinter(Logger logger) {
        super(People.class, logger);
    }
}
