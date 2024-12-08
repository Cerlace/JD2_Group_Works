package itacademy.commands.people;

import itacademy.api.PeopleDAO;
import itacademy.commands.GetAllCommand;
import itacademy.entity.People;
import itacademy.printers.impl.PeoplePrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PeopleGetAllCommand extends GetAllCommand<People> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PeopleGetAllCommand.class);

    public PeopleGetAllCommand(PeopleDAO dao) {
        super(dao, new PeoplePrinter(LOGGER));
    }
}
