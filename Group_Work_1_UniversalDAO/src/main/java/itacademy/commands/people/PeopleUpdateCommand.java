package itacademy.commands.people;

import itacademy.api.DAO;
import itacademy.commands.UpdateCommand;
import itacademy.dto.People;

import java.io.Serializable;
import java.util.function.Supplier;

public class PeopleUpdateCommand extends UpdateCommand<People>{

    public PeopleUpdateCommand(DAO<People> dao,
                               Supplier<People> peopleSupplier,
                               Supplier<Serializable> idSupplier) {
        super(dao, peopleSupplier, idSupplier);
    }
}
