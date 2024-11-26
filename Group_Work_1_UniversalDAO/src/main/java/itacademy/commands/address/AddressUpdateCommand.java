package itacademy.commands.address;

import itacademy.api.Creator;
import itacademy.api.DAO;
import itacademy.commands.UpdateCommand;
import itacademy.dto.Address;
import itacademy.exceptions.checked.InvalidInputException;

import java.io.Serializable;
import java.sql.SQLException;

public class AddressUpdateCommand extends UpdateCommand<Address>{

    public AddressUpdateCommand(DAO<Address> dao,
                                Creator<Address> addressCreator,
                                Creator<Serializable> idCreator) {
        super(dao, addressCreator, idCreator);
    }

    @Override
    public void execute() throws SQLException, InvalidInputException {
        super.execute();
        if (super.isUpdated) {
            System.out.println("Информация об адресе обновлена!");
        } else {
            System.out.println("Адрес с таким id не найден!");
        }
    }
}
