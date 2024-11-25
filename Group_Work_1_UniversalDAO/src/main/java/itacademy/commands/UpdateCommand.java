package itacademy.commands;

import itacademy.api.DAO;
import itacademy.api.Command;
import itacademy.utils.ReflectionUtils;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.function.Supplier;

public abstract class UpdateCommand<T> implements Command {
    private final DAO<T> dao;
    private final Supplier<T> entitySupplier;
    private final Supplier<Serializable> idSupplier;

    public UpdateCommand(DAO<T> dao, Supplier<T> entitySupplier, Supplier<Serializable> idSupplier) {
        this.dao = dao;
        this.entitySupplier = entitySupplier;
        this.idSupplier = idSupplier;
    }
    @Override
    public void execute() throws SQLException {
        Serializable id = idSupplier.get();
        T entity = entitySupplier.get();

        int updatedRows = this.dao.update(id,entity);
        if (updatedRows != 0) {
            System.out.println("В таблице " + ReflectionUtils.getTableNameByClass(entity.getClass())
                    + " обновлена запись с id " + id);
        } else {
            System.out.println("Не найдена запись с id " + id);
        }
    }
}
