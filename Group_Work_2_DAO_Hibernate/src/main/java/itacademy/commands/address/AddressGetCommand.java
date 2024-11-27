package itacademy.commands.address;

import itacademy.api.Creator;
import itacademy.api.DAO;
import itacademy.commands.GetCommand;
import itacademy.dto.Address;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.printers.AddressPrinter;

import java.io.Serializable;
import java.sql.SQLException;

public class AddressGetCommand extends GetCommand<Address> {

    public AddressGetCommand(DAO<Address> dao, Creator<Serializable> idCreator) {
        super(dao, idCreator);
    }

    @Override
    public void execute() throws SQLException, InvalidInputException {
        super.execute();
        new AddressPrinter().printEntity(super.receivedEntity);
    }
}
