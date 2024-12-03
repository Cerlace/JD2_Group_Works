package itacademy.commands;

import itacademy.api.Creator;
import itacademy.api.DAO;
import itacademy.api.Command;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.DataOutputUtils;

import java.io.Serializable;

public abstract class DeleteCommand<T> implements Command {
    private final DAO<T> dao;
    private final Creator<Serializable> idCreator;

    public DeleteCommand(DAO<T> dao, Creator<Serializable> idCreator) {
        this.dao = dao;
        this.idCreator = idCreator;
    }

    @Override
    public void execute() throws InvalidInputException {
        Serializable id = this.idCreator.create();
        if (this.dao.delete(id)) {
            DataOutputUtils.displayMessage("Запись с id " + id + " удалена");
        } else {
            DataOutputUtils.displayMessage("Запись с id " + id + " не найдена!");
        }
    }
}
