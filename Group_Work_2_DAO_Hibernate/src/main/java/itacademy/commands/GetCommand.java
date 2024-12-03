package itacademy.commands;

import itacademy.api.Creator;
import itacademy.api.DAO;
import itacademy.api.Command;
import itacademy.api.Printer;
import itacademy.exceptions.checked.InvalidInputException;

import java.io.Serializable;

public abstract class GetCommand<T> implements Command {
    private final DAO<T> dao;
    private final Creator<Serializable> idCreator;
    private final Printer<T> printer;

    public GetCommand(DAO<T> dao, Creator<Serializable> idCreator, Printer<T> printer) {
        this.dao = dao;
        this.idCreator = idCreator;
        this.printer = printer;
    }

    @Override
    public void execute() throws InvalidInputException, IllegalAccessException {
        Serializable id = idCreator.create();
        printer.printEntity(dao.get(id));
    }
}
