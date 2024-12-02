package itacademy.commands.people;

import itacademy.commands.UpdateCommand;
import itacademy.creators.IdCreator;
import itacademy.creators.PeopleCreator;
import itacademy.dao.impl.PeopleDAOImpl;
import itacademy.entity.People;

public class PeopleUpdateCommand extends UpdateCommand<People> {

    public PeopleUpdateCommand() {
        super(new PeopleDAOImpl(), new PeopleCreator(), new IdCreator());
    }
}
