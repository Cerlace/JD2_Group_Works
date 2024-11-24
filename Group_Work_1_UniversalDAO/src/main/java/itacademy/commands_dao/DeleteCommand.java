package itacademy.commands_dao;

import itacademy.api.DAO;
import itacademy.api.CommandDAO;
import itacademy.utils.ReflectionUtils;

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
        if (deletedRows != 0) {
            System.out.println("Удалена запись с id " + this.id);
        } else {
            System.out.println("Не найдена запись с id " + this.id);
        }
    }
}
