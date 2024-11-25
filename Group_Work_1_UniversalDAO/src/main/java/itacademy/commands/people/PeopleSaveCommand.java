package itacademy.commands.people;

import itacademy.api.DAO;
import itacademy.commands.SaveCommand;
import itacademy.dto.People;

import java.util.function.Supplier;

public class PeopleSaveCommand extends SaveCommand<People> {

    public PeopleSaveCommand(DAO<People> dao, Supplier<People> peopleSupplier) {
        super(dao, peopleSupplier);
    }
}
