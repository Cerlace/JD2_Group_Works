package itacademy.commands.address;

import itacademy.api.AddressDAO;
import itacademy.commands.SaveCommand;
import itacademy.creators.AddressCreator;
import itacademy.entity.Address;
import itacademy.printers.impl.AddressPrinter;

public class AddressSaveCommand extends SaveCommand<Address> {

    public AddressSaveCommand(AddressDAO dao) {
        super(dao, new AddressCreator(), new AddressPrinter());
    }
}
