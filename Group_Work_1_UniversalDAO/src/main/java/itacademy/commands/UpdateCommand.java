package itacademy.commands;

import itacademy.api.Creator;
import itacademy.api.DAO;
import itacademy.api.Command;
import itacademy.exceptions.checked.InvalidInputException;

import java.io.Serializable;
import java.sql.SQLException;

public abstract class UpdateCommand<T> implements Command {
    private final DAO<T> dao;
    private final Creator<T> entityCreator;
    private final Creator<Serializable> idCreator;
    protected boolean isUpdated;

    public UpdateCommand(DAO<T> dao, Creator<T> entityCreator, Creator<Serializable> idCreator) {
        this.dao = dao;
        this.entityCreator = entityCreator;
        this.idCreator = idCreator;
    }
    @Override
    public void execute() throws SQLException, InvalidInputException {
        Serializable id = idCreator.create();
        T entity = entityCreator.create();

        if (this.dao.update(id,entity) != 0) {
            isUpdated = true;
        }
    }
}
