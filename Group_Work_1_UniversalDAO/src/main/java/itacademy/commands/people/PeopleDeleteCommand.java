package itacademy.commands.people;

import itacademy.api.DAO;
import itacademy.commands.DeleteCommand;
import itacademy.dto.People;

import java.io.Serializable;
import java.util.function.Supplier;

public class PeopleDeleteCommand extends DeleteCommand<People> {

    public PeopleDeleteCommand(DAO<People> dao, Supplier<Serializable> idSupplier) {
        super(dao, idSupplier);
    }
}
