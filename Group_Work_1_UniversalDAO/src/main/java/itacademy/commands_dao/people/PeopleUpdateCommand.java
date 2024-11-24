package itacademy.commands_dao.people;

import itacademy.api.DAO;
import itacademy.commands_dao.UpdateCommand;
import itacademy.dto.People;

import java.io.Serializable;

public class PeopleUpdateCommand extends UpdateCommand<People>{

    public PeopleUpdateCommand(DAO<People> dao, People entity, Serializable id) {
        super(dao, entity, id);
    }
}
