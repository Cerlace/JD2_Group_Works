package itacademy.commands.address;

import itacademy.api.AddressDAO;
import itacademy.commands.GetAllCommand;
import itacademy.entity.Address;
import itacademy.printers.impl.AddressPrinter;

public class AddressGetAllCommand extends GetAllCommand<Address> {

    public AddressGetAllCommand(AddressDAO dao) {
        super(dao, new AddressPrinter());
    }
}
