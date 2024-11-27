package itacademy.commands.address;

import itacademy.api.DAO;
import itacademy.commands.GetAllCommand;
import itacademy.dto.Address;
import itacademy.printers.AddressPrinter;

import java.sql.SQLException;

public class AddressGetAllCommand extends GetAllCommand<Address> {

    public AddressGetAllCommand(DAO<Address> dao) {
        super(dao);
    }

    @Override
    public void execute() throws SQLException {
        super.execute();
        new AddressPrinter().printAllEntities(super.entities);
    }
}
