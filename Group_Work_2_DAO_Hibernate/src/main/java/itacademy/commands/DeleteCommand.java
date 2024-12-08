package itacademy.commands;

import itacademy.api.Creator;
import itacademy.api.DAO;
import itacademy.api.Command;
import itacademy.exceptions.checked.InvalidInputException;
import org.slf4j.Logger;

import java.io.Serializable;

public abstract class DeleteCommand<T> implements Command {
    private static final String ROW_DELETED = "Запись с id {} удалена";
    private static final String ROW_NOT_FOUND = "Запись с id {} не найдена!";
    private final Logger logger;

    private final DAO<T> dao;
    private final Creator<Serializable> idCreator;

    public DeleteCommand(DAO<T> dao, Creator<Serializable> idCreator, Logger logger) {
        this.dao = dao;
        this.idCreator = idCreator;
        this.logger = logger;
    }

    @Override
    public void execute() throws InvalidInputException {
        Serializable id = this.idCreator.create();
        if (this.dao.delete(id)) {
            this.logger.debug(ROW_DELETED, id);
        } else {
            this.logger.debug(ROW_NOT_FOUND, id);
        }
    }
}
