package itacademy.commands.people;

import itacademy.api.PeopleDAO;
import itacademy.commands.SaveCommand;
import itacademy.creators.PeopleCreator;
import itacademy.entity.People;
import itacademy.printers.impl.PeoplePrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PeopleSaveCommand extends SaveCommand<People> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PeopleSaveCommand.class);

    public PeopleSaveCommand(PeopleDAO dao) {
        super(dao, new PeopleCreator(LOGGER), new PeoplePrinter(LOGGER), LOGGER);
    }
}
