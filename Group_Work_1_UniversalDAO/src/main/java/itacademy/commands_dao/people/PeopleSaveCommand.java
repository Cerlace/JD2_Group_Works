package itacademy.commands_dao.people;

import itacademy.api.DAO;
import itacademy.commands_dao.SaveCommand;
import itacademy.dto.People;

public class PeopleSaveCommand extends SaveCommand<People> {

    public PeopleSaveCommand(DAO<People> dao, People people) {
        super(dao, people);
    }
}
