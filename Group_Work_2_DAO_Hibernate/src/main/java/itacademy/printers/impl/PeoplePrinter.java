package itacademy.printers.impl;

import itacademy.api.Printer;
import itacademy.entity.People;
import itacademy.printers.UniversalPrinter;

public class PeoplePrinter extends UniversalPrinter<People> implements Printer<People> {

    public PeoplePrinter() {
        super(People.class);
    }
}
