package itacademy.commands_dao;

import itacademy.api.DAO;
import itacademy.api.Command;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.ReflectionUtils;

import java.sql.SQLException;

public abstract class SaveCommand<T> implements Command {
    private final DAO<T> dao;
    private T entity;

    public SaveCommand(DAO<T> dao) {
        this.dao = dao;
    }

    @Override
    public T execute() throws SQLException, InvalidInputException {
        T receivedObject = this.dao.save(this.entity);
        System.out.println("В таблицу " + ReflectionUtils.getTableNameByClass(this.entity.getClass()) + " добавлена запись:");
        System.out.println(receivedObject);
        return receivedObject;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
