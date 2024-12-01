package itacademy.commands.people;

import itacademy.commands.GetAllCommand;
import itacademy.dao.impl.PeopleDAOImpl;
import itacademy.entity.People;
import itacademy.printers.impl.PeoplePrinter;

public class PeopleGetAllCommand extends GetAllCommand<People> {

    public PeopleGetAllCommand() {
        super(new PeopleDAOImpl(), new PeoplePrinter());
    }
}
