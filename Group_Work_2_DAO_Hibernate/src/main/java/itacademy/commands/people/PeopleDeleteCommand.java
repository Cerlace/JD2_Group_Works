package itacademy.commands.people;

import itacademy.commands.DeleteCommand;
import itacademy.creators.IdCreator;
import itacademy.dao.impl.PeopleDAOImpl;
import itacademy.entity.People;

public class PeopleDeleteCommand extends DeleteCommand<People> {

    public PeopleDeleteCommand() {
        super(new PeopleDAOImpl(), new IdCreator());
    }
}
