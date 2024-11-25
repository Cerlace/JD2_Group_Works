package itacademy.commands_dao.people;

import itacademy.api.DAO;
import itacademy.commands_dao.GetAllCommand;
import itacademy.dto.People;

import java.sql.SQLException;
import java.util.List;

public class PeopleGetAllCommand extends GetAllCommand<People> {

    public PeopleGetAllCommand(DAO<People> dao) {
        super(dao);
    }

    @Override
    public List<People> execute() throws SQLException {
        return super.execute();
    }
}
