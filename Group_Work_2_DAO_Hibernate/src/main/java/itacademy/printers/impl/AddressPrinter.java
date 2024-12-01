package itacademy.printers.impl;

import itacademy.api.Printer;
import itacademy.entity.Address;
import itacademy.printers.UniversalPrinter;

public class AddressPrinter extends UniversalPrinter<Address> implements Printer<Address> {

    public AddressPrinter() {
        super(Address.class);
    }
}
