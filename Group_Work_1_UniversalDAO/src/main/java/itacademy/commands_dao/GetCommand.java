package itacademy.commands_dao;

import itacademy.api.DAO;
import itacademy.api.CommandDAO;
import itacademy.utils.ReflectionUtils;

import java.io.Serializable;
import java.sql.SQLException;

public abstract class GetCommand<T> implements CommandDAO {
    private final Serializable id;
    private final DAO<T> dao;

    public GetCommand(final Serializable id, final DAO<T> dao) {
        this.id = id;
        this.dao = dao;
    }
    @Override
    public void execute() throws SQLException {
        T receivedObject= this.dao.get(this.id);

        if (receivedObject != null) {
            System.out.println("Из таблицы " + ReflectionUtils.getTableNameByClass(receivedObject.getClass()) + " получена запись:");
            System.out.println(receivedObject);
        } else {
            System.out.println("Запись с таким id не найдена!");
        }
    }
}
