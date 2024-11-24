package itacademy.commands_dao;

import itacademy.api.DAO;
import itacademy.api.CommandDAO;

import java.io.Serializable;
import java.sql.SQLException;

public abstract class DeleteCommand<T> implements CommandDAO {
    private final DAO<T> dao;
    private final Serializable id;

    public DeleteCommand(DAO<T> dao, Serializable id) {
        this.dao = dao;
        this.id = id;
    }
    @Override
    public void execute() throws SQLException {
        int deletedRows = this.dao.delete(this.id);
        System.out.println("Удалено записей с id " + this.id + ": " + deletedRows);
    }
}
