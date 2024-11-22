package itacademy.commands_dao.people;

import itacademy.api.DAO;
import itacademy.commands_dao.GetCommand;
import itacademy.dto.People;

import java.io.Serializable;

public class PeopleGetCommand extends GetCommand<People> {

    public PeopleGetCommand(Serializable id, DAO<People> dao) {
        super(id, dao);
    }
}
