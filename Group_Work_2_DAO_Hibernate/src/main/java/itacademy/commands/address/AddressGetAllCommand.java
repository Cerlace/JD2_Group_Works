package itacademy.commands.address;

import itacademy.api.AddressDAO;
import itacademy.commands.GetAllCommand;
import itacademy.entity.Address;
import itacademy.printers.impl.AddressPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressGetAllCommand extends GetAllCommand<Address> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressGetAllCommand.class);

    public AddressGetAllCommand(AddressDAO dao) {
        super(dao, new AddressPrinter(LOGGER));
    }
}
