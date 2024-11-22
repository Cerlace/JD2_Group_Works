package itacademy.commands_dao.people;

import itacademy.api.DAO;
import itacademy.commands_dao.DeleteCommand;
import itacademy.dto.People;

import java.io.Serializable;
import java.sql.SQLException;

public class PeopleDeleteCommand extends DeleteCommand<People> {
    private final Serializable id;

    public PeopleDeleteCommand(DAO<People> dao, Serializable id) {
        super(dao, id);
        this.id = id;
    }

    @Override
    public void execute() throws SQLException {
        super.execute();
        System.out.println("Удален человек с id = " + this.id);
    }
}
