package itacademy.commands.address;

import itacademy.api.Creator;
import itacademy.api.DAO;
import itacademy.commands.DeleteCommand;
import itacademy.dto.Address;
import itacademy.exceptions.checked.InvalidInputException;

import java.io.Serializable;
import java.sql.SQLException;

public class AddressDeleteCommand extends DeleteCommand<Address> {

    public AddressDeleteCommand(DAO<Address> dao, Creator<Serializable> idCreator) {
        super(dao, idCreator);
    }

    @Override
    public void execute() throws SQLException, InvalidInputException {
        super.execute();

        if (super.isDeleted) {
            System.out.println("Из таблицы ADDRESS удалена запись!");
        } else {
            System.out.println("Не найдена запись с таким id!");
        }
    }
}
