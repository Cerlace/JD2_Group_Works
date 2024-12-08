package itacademy.commands.address;

import itacademy.api.AddressDAO;
import itacademy.commands.SaveCommand;
import itacademy.creators.AddressCreator;
import itacademy.entity.Address;
import itacademy.printers.impl.AddressPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressSaveCommand extends SaveCommand<Address> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressSaveCommand.class);

    public AddressSaveCommand(AddressDAO dao) {
        super(dao, new AddressCreator(LOGGER), new AddressPrinter(LOGGER), LOGGER);
    }
}
