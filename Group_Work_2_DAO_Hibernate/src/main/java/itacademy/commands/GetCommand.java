package itacademy.commands;

import itacademy.api.Creator;
import itacademy.api.DAO;
import itacademy.api.Command;
import itacademy.exceptions.checked.InvalidInputException;

import java.io.Serializable;
import java.sql.SQLException;

public abstract class GetCommand<T> implements Command {
    private final DAO<T> dao;
    private final Creator<Serializable> idCreator;
    protected T receivedEntity;

    public GetCommand(DAO<T> dao, Creator<Serializable> idCreator) {
        this.dao = dao;
        this.idCreator = idCreator;
    }
    @Override
    public void execute() throws SQLException, InvalidInputException {
        Serializable id = idCreator.create();
        this.receivedEntity = dao.get(id);
    }
}
