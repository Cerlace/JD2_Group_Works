package itacademy.commands.address;

import itacademy.commands.GetAllCommand;
import itacademy.dao.impl.AddressDAOImpl;
import itacademy.entity.Address;
import itacademy.printers.impl.AddressPrinter;

public class AddressGetAllCommand extends GetAllCommand<Address> {

    public AddressGetAllCommand() {
        super(new AddressDAOImpl(), new AddressPrinter());
    }
}
