package itacademy.commands_dao.people;

import itacademy.api.DAO;
import itacademy.commands_dao.GetCommand;
import itacademy.dto.People;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.ConsoleUtils;
import itacademy.utils.DataPrinterUtils;
import itacademy.utils.ReflectionUtils;

import java.io.Serializable;
import java.sql.SQLException;

public class PeopleGetCommand extends GetCommand<People> {

    public PeopleGetCommand(DAO<People> dao) {
        super(dao);
    }

    @Override
    public People execute() throws SQLException, InvalidInputException {
        setId(ConsoleUtils.inputId());
        People receivedObject = super.execute();
        if (receivedObject != null) {
            System.out.println("Из таблицы " + ReflectionUtils.getTableNameByClass(receivedObject.getClass()) + " получена запись:");
            DataPrinterUtils.printOnePeople(receivedObject);
        } else {
            System.out.println("Запись с таким id не найдена!");
        }
        return receivedObject;
    }
}
