package itacademy.commands_dao.people;

import itacademy.api.DAO;
import itacademy.commands_dao.DeleteCommand;
import itacademy.dto.People;

import java.io.Serializable;

public class PeopleDeleteCommand extends DeleteCommand<People> {

    public PeopleDeleteCommand(DAO<People> dao, Serializable id) {
        super(dao, id);
    }
}
