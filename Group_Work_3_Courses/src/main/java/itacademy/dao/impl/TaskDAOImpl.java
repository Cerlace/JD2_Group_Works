package itacademy.dao.impl;

import itacademy.api.TaskDAO;
import itacademy.dao.DAO;
import itacademy.entity.Course;
import itacademy.entity.Task;
import itacademy.utils.ExecutorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class TaskDAOImpl extends DAO<Task> implements TaskDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskDAOImpl.class);
    private static final String SELECT_ONE_LOG_MESSAGE = "Table: '{}', starting select single result query";
    private static final String SELECT_MANY_LOG_MESSAGE = "Table: '{}', starting select many results query";
    private static final String SELECT_NEWEST_TASK_BY_COURSE_QUERY =
            "SELECT task FROM Task task WHERE task.course = :course ORDER BY task.taskDate DESC";
    private static final String SELECT_TASKS_EARLIER_THAN_QUERY =
            "SELECT task FROM Task task WHERE task.taskDate <= :date";
    private final EntityManager em;

    public TaskDAOImpl() {
        super(Task.class, LOGGER);
        this.em = super.getEntityManager();
    }

    @Override
    public Task getNewestTaskByCourse(Course course) {
        return ExecutorUtils.executeHibernate(this.em, em -> {
            LOGGER.info(SELECT_ONE_LOG_MESSAGE, super.getTableName());
            TypedQuery<Task> query = em.createQuery(SELECT_NEWEST_TASK_BY_COURSE_QUERY, Task.class);
            query.setParameter("course", course);
            query.setMaxResults(1);
            return query.getSingleResult();
        });
    }

    @Override
    public List<Task> getTasksEarlierThan(Date date) {
        return ExecutorUtils.executeHibernate(this.em, em -> {
            LOGGER.info(SELECT_MANY_LOG_MESSAGE, super.getTableName());
            TypedQuery<Task> query = em.createQuery(SELECT_TASKS_EARLIER_THAN_QUERY, Task.class);
            query.setParameter("date", date);
            return query.getResultList();
        });
    }
}
