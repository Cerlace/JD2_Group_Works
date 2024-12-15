package itacademy.dao.impl;

import itacademy.api.GradeDAO;
import itacademy.dao.DAO;
import itacademy.entity.Course;
import itacademy.entity.Grade;
import itacademy.entity.Student;
import itacademy.entity.Task;
import itacademy.utils.ExecutorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.Set;

public class GradeDAOImpl extends DAO<Grade> implements GradeDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(GradeDAOImpl.class);
    private final EntityManager em;

    public GradeDAOImpl() {
        super(Grade.class, LOGGER);
        this.em = super.getEntityManager();
    }


    @Override
    public Set<Grade> getStudentsGradesByCourse(Course course, Student student) {
        return ExecutorUtils.executeHibernate(this.em, em -> {
            Course foundCourse = em.find(Course.class, course.getId());

            if (foundCourse != null) {
                Set<Task> coursesTasks = foundCourse.getTasks();
                Query query = em.createQuery("SELECT g FROM Grade g WHERE g.task = :task AND g.student= :student");
                query.setParameter("student", student);
                Set<Grade> resultGrades = new HashSet<>();
                if (coursesTasks != null) {
                    for (Task coursesTask : coursesTasks) {
                        query.setParameter("task", coursesTask);
                        resultGrades.addAll(query.getResultList());
                    }

                    return resultGrades;
                }
            }

            return null;
        });
    }
}
