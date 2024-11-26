package itacademy.commands.address;

import itacademy.api.Creator;
import itacademy.api.DAO;
import itacademy.commands.SaveCommand;
import itacademy.dto.Address;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.printers.AddressPrinter;

import java.sql.SQLException;

public class AddressSaveCommand extends SaveCommand<Address> {

    public AddressSaveCommand(DAO<Address> dao, Creator<Address> addressCreator) {
        super(dao, addressCreator);
    }

    @Override
    public void execute() throws SQLException, InvalidInputException {
        super.execute();
        System.out.println("В таблицу ADDRESS добавлена запись");
        new AddressPrinter().printEntity(super.receivedEntity);
    }
}
