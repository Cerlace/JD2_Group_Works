package itacademy.commands_dao;

import itacademy.api.DAO;
import itacademy.api.CommandDAO;
import itacademy.utils.ReflectionUtils;

import java.sql.SQLException;

public abstract class SaveCommand<T> implements CommandDAO {
    private final DAO<T> dao;
    private final T entity;

    public SaveCommand(DAO<T> dao, T t) {
        this.dao = dao;
        this.entity = t;
    }

    @Override
    public void execute() throws SQLException {
        T receivedObject = this.dao.save(this.entity);
        System.out.println("В таблицу " + ReflectionUtils.getTableNameByClass(this.entity.getClass()) + " добавлена запись:");
        System.out.println(receivedObject);
    }
}
