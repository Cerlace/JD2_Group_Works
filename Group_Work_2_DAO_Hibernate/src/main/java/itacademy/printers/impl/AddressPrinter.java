package itacademy.printers.impl;

import itacademy.api.Printer;
import itacademy.entity.Address;
import itacademy.printers.UniversalPrinter;
import org.slf4j.Logger;

public class AddressPrinter extends UniversalPrinter<Address> implements Printer<Address> {

    public AddressPrinter(Logger logger) {
        super(Address.class, logger);
    }
}
