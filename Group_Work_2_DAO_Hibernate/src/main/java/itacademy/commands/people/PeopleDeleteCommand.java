package itacademy.commands.people;

import itacademy.api.PeopleDAO;
import itacademy.commands.DeleteCommand;
import itacademy.creators.IdCreator;
import itacademy.entity.People;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PeopleDeleteCommand extends DeleteCommand<People> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PeopleDeleteCommand.class);

    public PeopleDeleteCommand(PeopleDAO dao) {
        super(dao, new IdCreator(LOGGER), LOGGER);
    }
}
