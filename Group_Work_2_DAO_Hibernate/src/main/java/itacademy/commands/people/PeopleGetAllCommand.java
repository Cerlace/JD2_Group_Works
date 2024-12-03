package itacademy.commands.people;

import itacademy.api.PeopleDAO;
import itacademy.commands.GetAllCommand;
import itacademy.entity.People;
import itacademy.printers.impl.PeoplePrinter;

public class PeopleGetAllCommand extends GetAllCommand<People> {

    public PeopleGetAllCommand(PeopleDAO dao) {
        super(dao, new PeoplePrinter());
    }
}
