package itacademy.dao.impl;

import itacademy.api.TaskDAO;
import itacademy.dao.DAO;
import itacademy.entity.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskDAOImpl extends DAO<Task> implements TaskDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskDAOImpl.class);

    public TaskDAOImpl() {
        super(Task.class, LOGGER);
    }
}
