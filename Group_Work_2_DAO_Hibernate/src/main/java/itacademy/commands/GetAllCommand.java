package itacademy.commands;

import itacademy.api.DAO;
import itacademy.api.Command;
import itacademy.api.Printer;

import java.sql.SQLException;

public abstract class GetAllCommand<T> implements Command {
    private final DAO<T> dao;
    private final Printer<T> printer;

    public GetAllCommand(DAO<T> dao, Printer<T> printer) {
        this.dao = dao;
        this.printer = printer;
    }

    @Override
    public void execute() throws SQLException, IllegalAccessException {
        printer.printAllEntities(this.dao.getAll());
    }
}
