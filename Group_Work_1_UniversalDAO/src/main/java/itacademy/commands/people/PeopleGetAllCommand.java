package itacademy.commands.people;

import itacademy.api.DAO;
import itacademy.commands.GetAllCommand;
import itacademy.dto.People;
import itacademy.printers.PeoplePrinter;

import java.sql.SQLException;

public class PeopleGetAllCommand extends GetAllCommand<People> {

    public PeopleGetAllCommand(DAO<People> dao) {
        super(dao);
    }

    @Override
    public void execute() throws SQLException {
        super.execute();
        new PeoplePrinter().printAllEntities(super.entities);
    }
}
