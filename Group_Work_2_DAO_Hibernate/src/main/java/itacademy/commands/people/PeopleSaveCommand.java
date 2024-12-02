package itacademy.commands.people;

import itacademy.commands.SaveCommand;
import itacademy.creators.PeopleCreator;
import itacademy.dao.impl.PeopleDAOImpl;
import itacademy.entity.People;
import itacademy.printers.impl.PeoplePrinter;

public class PeopleSaveCommand extends SaveCommand<People> {

    public PeopleSaveCommand() {
        super(new PeopleDAOImpl(), new PeopleCreator(), new PeoplePrinter());
    }
}
