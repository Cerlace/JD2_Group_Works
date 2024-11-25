package itacademy.commands_dao;

import itacademy.api.DAO;
import itacademy.api.Command;
import itacademy.exceptions.checked.InvalidInputException;

import java.io.Serializable;
import java.sql.SQLException;

public abstract class DeleteCommand<T> implements Command {
    private final DAO<T> dao;
    private Serializable id;

    public DeleteCommand(DAO<T> dao) {
        this.dao = dao;
    }

    @Override
    public T execute() throws SQLException, InvalidInputException {
        if (this.id != null) {
            int deletedRows = this.dao.delete(this.id);
            if (deletedRows != 0) {
                System.out.println("Удалена запись с id " + this.id);
            } else {
                System.out.println("Не найдена запись с id " + this.id);
            }
        }
        return null;
    }

    public void setId(Serializable id) {
        this.id = id;
    }
}
