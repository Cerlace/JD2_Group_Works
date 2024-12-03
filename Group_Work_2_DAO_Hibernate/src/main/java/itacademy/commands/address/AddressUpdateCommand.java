package itacademy.commands.address;

import itacademy.api.AddressDAO;
import itacademy.commands.UpdateCommand;
import itacademy.creators.AddressCreator;
import itacademy.creators.IdCreator;
import itacademy.entity.Address;

public class AddressUpdateCommand extends UpdateCommand<Address> {

    public AddressUpdateCommand(AddressDAO dao) {
        super(dao, new AddressCreator(), new IdCreator());
    }
}
