package itacademy.commands.address;

import itacademy.api.AddressDAO;
import itacademy.commands.UpdateCommand;
import itacademy.creators.AddressCreator;
import itacademy.creators.IdCreator;
import itacademy.entity.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressUpdateCommand extends UpdateCommand<Address> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressUpdateCommand.class);

    public AddressUpdateCommand(AddressDAO dao) {
        super(dao, new AddressCreator(LOGGER), new IdCreator(LOGGER), LOGGER);
    }
}
