package itacademy.commands.people;

import itacademy.commands.GetCommand;
import itacademy.creators.IdCreator;
import itacademy.dao.impl.PeopleDAOImpl;
import itacademy.entity.People;
import itacademy.printers.impl.PeoplePrinter;

public class PeopleGetCommand extends GetCommand<People> {

    public PeopleGetCommand() {
        super(new PeopleDAOImpl(), new IdCreator(), new PeoplePrinter());
    }
}
