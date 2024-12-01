package itacademy.commands.address;

import itacademy.commands.SaveCommand;
import itacademy.creators.AddressCreator;
import itacademy.dao.impl.AddressDAOImpl;
import itacademy.entity.Address;
import itacademy.printers.impl.AddressPrinter;

public class AddressSaveCommand extends SaveCommand<Address> {

    public AddressSaveCommand() {
        super(new AddressDAOImpl(), new AddressCreator(), new AddressPrinter());
    }
}
