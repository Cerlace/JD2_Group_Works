package itacademy.commands.address;

import itacademy.api.AddressDAO;
import itacademy.commands.GetCommand;
import itacademy.creators.IdCreator;
import itacademy.entity.Address;
import itacademy.printers.impl.AddressPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressGetCommand extends GetCommand<Address> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressGetCommand.class);

    public AddressGetCommand(AddressDAO dao) {
        super(dao, new IdCreator(LOGGER), new AddressPrinter(LOGGER));
    }
}
