package itacademy.commands;

import itacademy.api.DAO;
import itacademy.api.Command;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.function.Supplier;

public abstract class DeleteCommand<T> implements Command {
    private final DAO<T> dao;
    private final Supplier<Serializable> idSupplier;

    public DeleteCommand(DAO<T> dao, Supplier<Serializable> idSupplier) {
        this.dao = dao;
        this.idSupplier = idSupplier;
    }

    @Override
    public void execute() throws SQLException {
        Serializable id = idSupplier.get();

        int deletedRows = this.dao.delete(id);
        if (deletedRows != 0) {
            System.out.println("Удалена запись с id " + id);
        } else {
            System.out.println("Не найдена запись с id " + id);
        }
    }
}
