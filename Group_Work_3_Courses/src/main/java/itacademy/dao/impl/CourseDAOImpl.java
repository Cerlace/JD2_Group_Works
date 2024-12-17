package itacademy.dao.impl;

import itacademy.api.CourseDAO;
import itacademy.dao.DAO;
import itacademy.entity.Course;
import itacademy.utils.ExecutorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import java.util.Map;
import java.util.stream.Collectors;

public class CourseDAOImpl extends DAO<Course> implements CourseDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseDAOImpl.class);
    private static final String SELECT_ONE_LOG_MESSAGE = "Table: '{}', starting select single result query";
    private static final String SELECT_MANY_LOG_MESSAGE = "Table: '{}', starting select many results query";
    private static final String SELECT_COURSE_WITH_GREATEST_AVG_GRADES_QUERY =
            "SELECT course FROM Course course JOIN course.tasks task " +
                    "JOIN task.grades grade GROUP BY course ORDER BY AVG(grade.score) DESC";
    private static final String SELECT_COURSES_AND_STUDENTS_COUNT_QUERY =
            "SELECT course as courseObject, COUNT(student) as studentsCount FROM Course course " +
                    "LEFT JOIN course.students student GROUP BY course";
    private final EntityManager em;

    public CourseDAOImpl() {
        super(Course.class, LOGGER);
        this.em = super.getEntityManager();
    }

    @Override
    public Course getCourseWithGreatestAvgGrades() {
        return ExecutorUtils.executeHibernate(this.em, em -> {
            LOGGER.info(SELECT_ONE_LOG_MESSAGE, super.getTableName());
            TypedQuery<Course> query = em.createQuery(SELECT_COURSE_WITH_GREATEST_AVG_GRADES_QUERY, Course.class);
            query.setMaxResults(1);
            return query.getSingleResult();
        });
    }

    @Override
    public Map<Course, Integer> getCoursesAndStudentsCount() {
        return ExecutorUtils.executeHibernate(this.em, em -> {
            LOGGER.info(SELECT_MANY_LOG_MESSAGE, super.getTableName());
            TypedQuery<Tuple> query = em.createQuery(SELECT_COURSES_AND_STUDENTS_COUNT_QUERY, Tuple.class);
            return query.getResultStream()
                    .collect(
                            Collectors.toMap(
                                    tuple -> (Course) tuple.get("courseObject"),
                                    tuple -> ((Number) tuple.get("studentsCount")).intValue()
                            )
                    );
        });
    }
}
