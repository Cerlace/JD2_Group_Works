package itacademy.dao.impl;

import itacademy.api.TeacherDAO;
import itacademy.dao.DAO;
import itacademy.entity.Teacher;
import itacademy.utils.ExecutorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class TeacherDAOImpl extends DAO<Teacher> implements TeacherDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherDAOImpl.class);
    private static final String SELECT_ONE_LOG_MESSAGE = "Table: '{}', starting select single result query";
    private static final String SELECT_MANY_LOG_MESSAGE = "Table: '{}', starting select many results query";
    private static final String SELECT_TEACHER_WITH_GREATEST_TASK_COUNT_QUERY =
            "SELECT teacher FROM Teacher teacher JOIN teacher.courses course JOIN course.tasks task " +
                    "GROUP BY teacher ORDER BY COUNT(task) DESC";
    private static final String SELECT_TEACHERS_WITH_LEAST_COURSES_COUNT_QUERY =
            "SELECT t FROM Teacher t ORDER BY t.courses.size";
    private final EntityManager em;

    public TeacherDAOImpl() {
        super(Teacher.class, LOGGER);
        this.em = super.getEntityManager();
    }

    @Override
    public Teacher getTeacherWithGreatestTaskCount() {
        return ExecutorUtils.executeHibernate(this.em, em -> {
            LOGGER.info(SELECT_ONE_LOG_MESSAGE, super.getTableName());
            TypedQuery<Teacher> query = em.createQuery(SELECT_TEACHER_WITH_GREATEST_TASK_COUNT_QUERY, Teacher.class);
            query.setMaxResults(1);
            return query.getSingleResult();
        });
    }

    @Override
    public List<Teacher> getTeachersWithLeastCoursesCount(int count) {
        return ExecutorUtils.executeHibernate(this.em, em -> {
            LOGGER.info(SELECT_MANY_LOG_MESSAGE, super.getTableName());
            TypedQuery<Teacher> query = em.createQuery(SELECT_TEACHERS_WITH_LEAST_COURSES_COUNT_QUERY, Teacher.class);
            query.setMaxResults(count);
            return query.getResultList();
        });
    }
}
