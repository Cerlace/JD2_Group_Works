package itacademy.commands_dao;

import itacademy.api.DAO;
import itacademy.api.Command;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.ReflectionUtils;

import java.io.Serializable;
import java.sql.SQLException;

public abstract class UpdateCommand<T> implements Command {
    private final DAO<T> dao;
    private T entity;
    private Serializable id;

    public UpdateCommand(DAO<T> dao) {
        this.dao = dao;
    }

    @Override
    public T execute() throws SQLException, InvalidInputException {
        int updatedRows = this.dao.update(this.id, this.entity);
        if (updatedRows != 0) {
            System.out.println("В таблице " + ReflectionUtils.getTableNameByClass(this.entity.getClass())
                    + " обновлена запись с id " + this.id);
        } else {
            System.out.println("Не найдена запись с id " + this.id);
        }
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public void setId(Serializable id) {
        this.id = id;
    }
}
