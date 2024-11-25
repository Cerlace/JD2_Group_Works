package itacademy.commands;

import itacademy.api.DAO;
import itacademy.api.Command;
import itacademy.utils.ReflectionUtils;

import java.sql.SQLException;
import java.util.function.Supplier;

public abstract class SaveCommand<T> implements Command {
    private final DAO<T> dao;
    private final Supplier<T> entitySupplier;

    public SaveCommand(DAO<T> dao, Supplier<T> entitySupplier) {
        this.dao = dao;
        this.entitySupplier = entitySupplier;
    }

    @Override
    public void execute() throws SQLException {
        T entity = entitySupplier.get();

        T receivedObject = this.dao.save(entity);
        System.out.println("В таблицу " + ReflectionUtils.getTableNameByClass(entity.getClass()) + " добавлена запись:");
        System.out.println(receivedObject);
    }
}
