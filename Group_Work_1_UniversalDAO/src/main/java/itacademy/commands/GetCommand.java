package itacademy.commands;

import itacademy.api.DAO;
import itacademy.api.Command;
import itacademy.utils.ReflectionUtils;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.function.Supplier;

public abstract class GetCommand<T> implements Command {
    private final DAO<T> dao;
    private final Supplier<Serializable> idSupplier;

    public GetCommand(DAO<T> dao, Supplier<Serializable> idSupplier) {
        this.dao = dao;
        this.idSupplier = idSupplier;
    }
    @Override
    public void execute() throws SQLException {
        Serializable id = idSupplier.get();

        T receivedObject= this.dao.get(id);

        if (receivedObject != null) {
            System.out.println("Из таблицы " + ReflectionUtils.getTableNameByClass(receivedObject.getClass()) + " получена запись:");
            System.out.println(receivedObject);
        } else {
            System.out.println("Запись с таким id не найдена!");
        }
    }
}
