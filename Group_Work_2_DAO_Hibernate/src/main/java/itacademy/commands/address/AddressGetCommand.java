package itacademy.commands.address;

import itacademy.api.AddressDAO;
import itacademy.commands.GetCommand;
import itacademy.creators.IdCreator;
import itacademy.entity.Address;
import itacademy.printers.impl.AddressPrinter;

public class AddressGetCommand extends GetCommand<Address> {

    public AddressGetCommand(AddressDAO dao) {
        super(dao, new IdCreator(), new AddressPrinter());
    }
}
