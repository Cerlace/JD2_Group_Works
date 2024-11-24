package itacademy.commands_dao;

import itacademy.api.DAO;
import itacademy.api.CommandDAO;
import itacademy.utils.ReflectionUtils;

import java.sql.SQLException;
import java.util.List;

public abstract class GetAllCommand<T> implements CommandDAO {
    private final DAO<T> dao;

    public GetAllCommand(DAO<T> dao) {
        this.dao = dao;
    }

    @Override
    public void execute() throws SQLException {
        List<T> items = dao.getAll();

        if (!items.isEmpty()) {
            System.out.println("Все записи таблицы " + ReflectionUtils.getTableNameByClass(items.get(0).getClass()));
            for (T item : items) {
                System.out.println(item);
            }
        } else {
            System.out.println("Записей не найдено! Таблица пуста!");
        }
    }
}
