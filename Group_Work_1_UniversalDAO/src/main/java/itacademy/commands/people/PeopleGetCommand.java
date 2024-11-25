package itacademy.commands.people;

import itacademy.api.DAO;
import itacademy.commands.GetCommand;
import itacademy.dto.People;

import java.io.Serializable;
import java.util.function.Supplier;

public class PeopleGetCommand extends GetCommand<People> {

    public PeopleGetCommand(DAO<People> dao, Supplier<Serializable> idSupplier) {
        super(dao, idSupplier);
    }
}
