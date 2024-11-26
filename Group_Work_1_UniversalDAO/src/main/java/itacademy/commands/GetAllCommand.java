package itacademy.commands;

import itacademy.api.DAO;
import itacademy.api.Command;

import java.sql.SQLException;
import java.util.List;

public abstract class GetAllCommand<T> implements Command {
    private final DAO<T> dao;
    protected List<T> entities;

    public GetAllCommand(DAO<T> dao) {
        this.dao = dao;
    }

    @Override
    public void execute() throws SQLException {
        this.entities = dao.getAll();
    }
}
