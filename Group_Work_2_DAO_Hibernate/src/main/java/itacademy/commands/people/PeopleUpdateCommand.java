package itacademy.commands.people;

import itacademy.api.PeopleDAO;
import itacademy.commands.UpdateCommand;
import itacademy.creators.IdCreator;
import itacademy.creators.PeopleCreator;
import itacademy.entity.People;

public class PeopleUpdateCommand extends UpdateCommand<People> {

    public PeopleUpdateCommand(PeopleDAO dao) {
        super(dao, new PeopleCreator(), new IdCreator());
    }
}
