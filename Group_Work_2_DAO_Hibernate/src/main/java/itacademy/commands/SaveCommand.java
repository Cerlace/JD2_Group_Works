package itacademy.commands;

import itacademy.api.Creator;
import itacademy.api.DAO;
import itacademy.api.Command;
import itacademy.api.Printer;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.DataOutputUtils;
import itacademy.utils.ReflectionUtils;

import java.sql.SQLException;

public abstract class SaveCommand<T> implements Command {
    private final DAO<T> dao;
    private final Creator<T> entityCreator;
    private final Printer<T> printer;

    public SaveCommand(DAO<T> dao, Creator<T> entityCreator, Printer<T> printer) {
        this.dao = dao;
        this.entityCreator = entityCreator;
        this.printer = printer;
    }

    @Override
    public void execute() throws SQLException, InvalidInputException, IllegalAccessException {
        T entity = this.entityCreator.create();
        DataOutputUtils.displayMessage("В таблицу "
                + ReflectionUtils.getTableNameByClass(entity.getClass())
                + " добавлена запись");
        printer.printEntity(this.dao.save(entity));
    }
}
