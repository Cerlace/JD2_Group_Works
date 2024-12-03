package itacademy.commands;

import itacademy.api.Command;
import itacademy.api.DAO;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.ConsoleUtils;
import itacademy.utils.HibernateUtils;

public class ExitCommand<T> implements Command {
    private final DAO<T> dao;

    public ExitCommand(DAO<T> dao) {
        this.dao = dao;
    }

    @Override
    public void execute() throws InvalidInputException, IllegalAccessException {
        dao.close();
        HibernateUtils.close();
        ConsoleUtils.closeScanner();
    }
}
