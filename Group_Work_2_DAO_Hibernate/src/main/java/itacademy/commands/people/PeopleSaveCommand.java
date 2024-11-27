package itacademy.commands.people;

import itacademy.api.Creator;
import itacademy.api.DAO;
import itacademy.commands.SaveCommand;
import itacademy.dto.People;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.printers.PeoplePrinter;

import java.sql.SQLException;

public class PeopleSaveCommand extends SaveCommand<People> {

    public PeopleSaveCommand(DAO<People> dao, Creator<People> peopleCreator) {
        super(dao, peopleCreator);
    }

    @Override
    public void execute() throws SQLException, InvalidInputException {
        super.execute();
        System.out.println("В таблицу PEOPLE добавлена запись");
        new PeoplePrinter().printEntity(super.receivedEntity);
    }
}
