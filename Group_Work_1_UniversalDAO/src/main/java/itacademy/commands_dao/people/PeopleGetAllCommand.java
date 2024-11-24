package itacademy.commands_dao.people;

import itacademy.api.DAO;
import itacademy.commands_dao.GetAllCommand;
import itacademy.dto.People;

public class PeopleGetAllCommand extends GetAllCommand<People> {

    public PeopleGetAllCommand(DAO<People> dao) {
        super(dao);
    }
}
