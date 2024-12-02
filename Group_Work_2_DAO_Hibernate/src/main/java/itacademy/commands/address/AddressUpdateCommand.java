package itacademy.commands.address;

import itacademy.commands.UpdateCommand;
import itacademy.creators.AddressCreator;
import itacademy.creators.IdCreator;
import itacademy.dao.impl.AddressDAOImpl;
import itacademy.entity.Address;

public class AddressUpdateCommand extends UpdateCommand<Address> {

    public AddressUpdateCommand() {
        super(new AddressDAOImpl(), new AddressCreator(), new IdCreator());
    }
}
