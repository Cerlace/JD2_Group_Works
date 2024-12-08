package itacademy.commands.people;

import itacademy.api.PeopleDAO;
import itacademy.commands.GetCommand;
import itacademy.creators.IdCreator;
import itacademy.entity.People;
import itacademy.printers.impl.PeoplePrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PeopleGetCommand extends GetCommand<People> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PeopleGetCommand.class);

    public PeopleGetCommand(PeopleDAO dao) {
        super(dao, new IdCreator(LOGGER), new PeoplePrinter(LOGGER));
    }
}
