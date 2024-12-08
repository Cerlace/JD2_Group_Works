package itacademy.commands.people;

import itacademy.api.PeopleDAO;
import itacademy.commands.UpdateCommand;
import itacademy.creators.IdCreator;
import itacademy.creators.PeopleCreator;
import itacademy.entity.People;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PeopleUpdateCommand extends UpdateCommand<People> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PeopleUpdateCommand.class);

    public PeopleUpdateCommand(PeopleDAO dao) {
        super(dao, new PeopleCreator(LOGGER), new IdCreator(LOGGER), LOGGER);
    }
}
