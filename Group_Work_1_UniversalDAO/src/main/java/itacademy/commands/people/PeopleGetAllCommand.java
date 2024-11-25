package itacademy.commands.people;

import itacademy.api.DAO;
import itacademy.commands.GetAllCommand;
import itacademy.dto.People;

public class PeopleGetAllCommand extends GetAllCommand<People> {

    public PeopleGetAllCommand(DAO<People> dao) {
        super(dao);
    }
}
