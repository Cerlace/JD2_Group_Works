package itacademy.commands.people;

import itacademy.api.Creator;
import itacademy.api.DAO;
import itacademy.commands.DeleteCommand;
import itacademy.dto.People;
import itacademy.exceptions.checked.InvalidInputException;

import java.io.Serializable;
import java.sql.SQLException;

public class PeopleDeleteCommand extends DeleteCommand<People> {

    public PeopleDeleteCommand(DAO<People> dao, Creator<Serializable> idCreator) {
        super(dao, idCreator);
    }

    @Override
    public void execute() throws SQLException, InvalidInputException {
        super.execute();

        if (super.isDeleted) {
            System.out.println("Из таблицы PEOPLE удалена запись!");
        } else {
            System.out.println("Не найдена запись с таким id!");
        }
    }
}
