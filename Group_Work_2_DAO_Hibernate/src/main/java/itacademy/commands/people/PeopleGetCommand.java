package itacademy.commands.people;

import itacademy.api.PeopleDAO;
import itacademy.commands.GetCommand;
import itacademy.creators.IdCreator;
import itacademy.entity.People;
import itacademy.printers.impl.PeoplePrinter;

public class PeopleGetCommand extends GetCommand<People> {

    public PeopleGetCommand(PeopleDAO dao) {
        super(dao, new IdCreator(), new PeoplePrinter());
    }
}
