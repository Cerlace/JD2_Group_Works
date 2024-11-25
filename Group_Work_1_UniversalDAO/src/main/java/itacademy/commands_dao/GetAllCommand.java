package itacademy.commands_dao;

import itacademy.api.DAO;
import itacademy.api.Command;
import itacademy.utils.ReflectionUtils;

import java.sql.SQLException;
import java.util.List;

public abstract class GetAllCommand<T> implements Command {
    private final DAO<T> dao;

    public GetAllCommand(DAO<T> dao) {
        this.dao = dao;
    }

    @Override
    public List<T> execute() throws SQLException {
        return dao.getAll();
    }
}
