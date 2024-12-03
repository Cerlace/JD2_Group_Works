package itacademy.commands.people;

import itacademy.api.PeopleDAO;
import itacademy.commands.DeleteCommand;
import itacademy.creators.IdCreator;
import itacademy.entity.People;

public class PeopleDeleteCommand extends DeleteCommand<People> {

    public PeopleDeleteCommand(PeopleDAO dao) {
        super(dao, new IdCreator());
    }
}
