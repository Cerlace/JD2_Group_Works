package itacademy.commands.address;

import itacademy.api.AddressDAO;
import itacademy.commands.DeleteCommand;
import itacademy.creators.IdCreator;
import itacademy.entity.Address;

public class AddressDeleteCommand extends DeleteCommand<Address> {

    public AddressDeleteCommand(AddressDAO dao) {
        super(dao, new IdCreator());
    }
}
