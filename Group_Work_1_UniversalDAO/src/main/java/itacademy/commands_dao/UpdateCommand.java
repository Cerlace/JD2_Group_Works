package itacademy.commands_dao;

import itacademy.api.DAO;
import itacademy.api.CommandDAO;
import itacademy.utils.ReflectionUtils;

import java.io.Serializable;
import java.sql.SQLException;

public abstract class UpdateCommand<T> implements CommandDAO {
    private final DAO<T> dao;
    private final T entity;
    private final Serializable id;

    public UpdateCommand(DAO<T> dao, T entity, Serializable id) {
        this.dao = dao;
        this.entity = entity;
        this.id = id;
    }
    @Override
    public void execute() throws SQLException {
        this.dao.update(this.id,this.entity);
        System.out.println("В таблице " + ReflectionUtils.getTableNameByClass(this.entity.getClass())
                + " обновлена запись с id " + this.id);
    }
}
