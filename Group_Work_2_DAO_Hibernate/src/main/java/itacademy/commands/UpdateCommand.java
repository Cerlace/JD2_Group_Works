package itacademy.commands;

import itacademy.api.Creator;
import itacademy.api.DAO;
import itacademy.api.Command;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.ReflectionUtils;
import org.slf4j.Logger;

import java.io.Serializable;

public abstract class UpdateCommand<T> implements Command {
    private static final String ROW_UPDATED = "Запись с id {} в таблице {} обновлена.";
    private static final String ROW_NOT_FOUND = "Запись с id {} не найдена!";

    private final DAO<T> dao;
    private final Creator<T> entityCreator;
    private final Creator<Serializable> idCreator;
    private final Logger logger;

    public UpdateCommand(DAO<T> dao, Creator<T> entityCreator, Creator<Serializable> idCreator, Logger logger) {
        this.dao = dao;
        this.entityCreator = entityCreator;
        this.idCreator = idCreator;
        this.logger = logger;
    }

    @Override
    public void execute() throws InvalidInputException, IllegalAccessException {
        Serializable id = this.idCreator.create();
        T entity = this.entityCreator.create();

        if (this.dao.update(id, entity) != null) {
            logger.debug(ROW_UPDATED, id, ReflectionUtils.getTableNameByClass(entity.getClass()));
        } else {
            logger.debug(ROW_NOT_FOUND, id);
        }
    }
}
