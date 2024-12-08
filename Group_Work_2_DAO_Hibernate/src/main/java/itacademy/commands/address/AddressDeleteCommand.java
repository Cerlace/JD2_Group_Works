package itacademy.commands.address;

import itacademy.api.AddressDAO;
import itacademy.commands.DeleteCommand;
import itacademy.creators.IdCreator;
import itacademy.entity.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressDeleteCommand extends DeleteCommand<Address> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressDeleteCommand.class);

    public AddressDeleteCommand(AddressDAO dao) {
        super(dao, new IdCreator(LOGGER), LOGGER);
    }
}
