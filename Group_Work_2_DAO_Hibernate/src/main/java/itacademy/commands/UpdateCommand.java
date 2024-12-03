package itacademy.commands;

import itacademy.api.Creator;
import itacademy.api.DAO;
import itacademy.api.Command;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.DataOutputUtils;
import itacademy.utils.ReflectionUtils;

import java.io.Serializable;

public abstract class UpdateCommand<T> implements Command {
    private final DAO<T> dao;
    private final Creator<T> entityCreator;
    private final Creator<Serializable> idCreator;

    public UpdateCommand(DAO<T> dao, Creator<T> entityCreator, Creator<Serializable> idCreator) {
        this.dao = dao;
        this.entityCreator = entityCreator;
        this.idCreator = idCreator;
    }

    @Override
    public void execute() throws InvalidInputException, IllegalAccessException {
        Serializable id = this.idCreator.create();
        T entity = this.entityCreator.create();

        if (this.dao.update(id, entity) != null) {
            DataOutputUtils.displayMessage("Запись с id " + id
            + " в таблице " + ReflectionUtils.getTableNameByClass(entity.getClass())
            + " обновлена.");
        } else {
            DataOutputUtils.displayMessage("Запись с id " + id + " не найдена!");
        }
    }
}
