package itacademy.commands.people;

import itacademy.api.Creator;
import itacademy.api.DAO;
import itacademy.commands.UpdateCommand;
import itacademy.dto.People;
import itacademy.exceptions.checked.InvalidInputException;

import java.io.Serializable;
import java.sql.SQLException;

public class PeopleUpdateCommand extends UpdateCommand<People>{

    public PeopleUpdateCommand(DAO<People> dao,
                               Creator<People> peopleCreator,
                               Creator<Serializable> idCreator) {
        super(dao, peopleCreator, idCreator);
    }

    @Override
    public void execute() throws SQLException, InvalidInputException {
        super.execute();
        if (super.isUpdated) {
            System.out.println("Информация о человеке обновлена!");
        } else {
            System.out.println("Человек с таким id не найден!");
        }
    }
}
