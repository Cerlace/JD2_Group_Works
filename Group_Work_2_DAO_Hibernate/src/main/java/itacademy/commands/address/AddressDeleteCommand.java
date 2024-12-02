package itacademy.commands.address;

import itacademy.commands.DeleteCommand;
import itacademy.creators.IdCreator;
import itacademy.dao.impl.AddressDAOImpl;
import itacademy.entity.Address;

public class AddressDeleteCommand extends DeleteCommand<Address> {

    public AddressDeleteCommand() {
        super(new AddressDAOImpl(), new IdCreator());
    }
}
