package itacademy.commands_dao.people;

import itacademy.api.DAO;
import itacademy.commands_dao.GetAllCommand;
import itacademy.dto.People;
import itacademy.utils.DataPrinterUtils;
import itacademy.utils.ReflectionUtils;

import java.sql.SQLException;
import java.util.List;

public class PeopleGetAllCommand extends GetAllCommand<People> {

    public PeopleGetAllCommand(DAO<People> dao) {
        super(dao);
    }

    @Override
    public List<People> execute() throws SQLException {
        List<People> items = super.execute();
        if (!items.isEmpty()) {
            System.out.println("Все записи таблицы " + ReflectionUtils.getTableNameByClass(items.get(0).getClass()));
            DataPrinterUtils.printAllPeople(items);
        } else {
            System.out.println("Записей не найдено! Таблица пуста!");
        }
        return items;
    }
}
