package itacademy.commands.people;

import itacademy.api.PeopleDAO;
import itacademy.commands.SaveCommand;
import itacademy.creators.PeopleCreator;
import itacademy.entity.People;
import itacademy.printers.impl.PeoplePrinter;

public class PeopleSaveCommand extends SaveCommand<People> {

    public PeopleSaveCommand(PeopleDAO dao) {
        super(dao, new PeopleCreator(), new PeoplePrinter());
    }
}
