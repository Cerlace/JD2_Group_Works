package itacademy.commands_dao;

import itacademy.api.DAO;
import itacademy.api.Command;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.ReflectionUtils;

import java.io.Serializable;
import java.sql.SQLException;

public abstract class GetCommand<T> implements Command {
    private final DAO<T> dao;
    private Serializable id;

    public GetCommand(DAO<T> dao) {
        this.dao = dao;
    }

    @Override
    public T execute() throws SQLException, InvalidInputException {
        T receivedObject = this.dao.get(this.id);
        if (receivedObject != null) {
            System.out.println("Из таблицы " + ReflectionUtils.getTableNameByClass(receivedObject.getClass()) + " получена запись:");
            System.out.println(receivedObject);
        } else {
            System.out.println("Запись с таким id не найдена!");
        }
        return receivedObject;
    }

    public void setId(Serializable id) {
        this.id = id;
    }
}
