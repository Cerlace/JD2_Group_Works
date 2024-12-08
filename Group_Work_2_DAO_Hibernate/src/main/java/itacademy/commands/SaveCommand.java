package itacademy.commands;

import itacademy.api.Creator;
import itacademy.api.DAO;
import itacademy.api.Command;
import itacademy.api.Printer;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.ReflectionUtils;
import org.slf4j.Logger;

public abstract class SaveCommand<T> implements Command {
    private static final String ROW_SAVED_MESSAGE = "В таблицу {} добавлена запись";

    private final DAO<T> dao;
    private final Creator<T> entityCreator;
    private final Printer<T> printer;
    private final Logger logger;

    public SaveCommand(DAO<T> dao, Creator<T> entityCreator, Printer<T> printer, Logger logger) {
        this.dao = dao;
        this.entityCreator = entityCreator;
        this.printer = printer;
        this.logger = logger;
    }

    @Override
    public void execute() throws InvalidInputException, IllegalAccessException {
        T entity = this.entityCreator.create();
        this.dao.save(entity);
        this.logger.debug(ROW_SAVED_MESSAGE, ReflectionUtils.getTableNameByClass(entity.getClass()));
        printer.printEntity(entity);
    }
}
