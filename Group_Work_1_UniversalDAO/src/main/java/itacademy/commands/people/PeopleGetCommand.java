package itacademy.commands.people;

import itacademy.api.Creator;
import itacademy.api.DAO;
import itacademy.commands.GetCommand;
import itacademy.dto.People;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.printers.PeoplePrinter;

import java.io.Serializable;
import java.sql.SQLException;

public class PeopleGetCommand extends GetCommand<People> {

    public PeopleGetCommand(DAO<People> dao, Creator<Serializable> idCreator) {
        super(dao, idCreator);
    }

    @Override
    public void execute() throws SQLException, InvalidInputException {
        super.execute();
        new PeoplePrinter().printEntity(super.receivedEntity);
    }
}
