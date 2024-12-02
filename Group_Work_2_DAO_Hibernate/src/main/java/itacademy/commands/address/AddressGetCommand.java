package itacademy.commands.address;

import itacademy.commands.GetCommand;
import itacademy.creators.IdCreator;
import itacademy.dao.impl.AddressDAOImpl;
import itacademy.entity.Address;
import itacademy.printers.impl.AddressPrinter;

public class AddressGetCommand extends GetCommand<Address> {

    public AddressGetCommand() {
        super(new AddressDAOImpl(), new IdCreator(), new AddressPrinter());
    }
}
