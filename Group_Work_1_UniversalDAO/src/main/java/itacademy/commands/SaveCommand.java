package itacademy.commands;

import itacademy.api.Creator;
import itacademy.api.DAO;
import itacademy.api.Command;
import itacademy.exceptions.checked.InvalidInputException;

import java.sql.SQLException;

public abstract class SaveCommand<T> implements Command {
    private final DAO<T> dao;
    private final Creator<T> entityCreator;
    protected T receivedEntity;

    public SaveCommand(DAO<T> dao, Creator<T> entityCreator) {
        this.dao = dao;
        this.entityCreator = entityCreator;
    }

    @Override
    public void execute() throws SQLException, InvalidInputException {
        T entity = entityCreator.create();
        this.receivedEntity = this.dao.save(entity);
    }
}
