package itacademy.commands_dao.people;

import itacademy.api.DAO;
import itacademy.commands_dao.SaveCommand;
import itacademy.dto.People;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.ConsoleUtils;

import java.sql.SQLException;

public class PeopleSaveCommand extends SaveCommand<People> {

    public PeopleSaveCommand(DAO<People> dao) {
        super(dao);
    }

    @Override
    public People execute() throws SQLException, InvalidInputException {
        setEntity(ConsoleUtils.inputPeopleData());
        return super.execute();
    }
}
