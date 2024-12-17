package itacademy.dao.impl;

import itacademy.api.GradeDAO;
import itacademy.dao.DAO;
import itacademy.entity.Course;
import itacademy.entity.Grade;
import itacademy.entity.Student;
import itacademy.utils.ExecutorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.Set;
import java.util.stream.Collectors;

public class GradeDAOImpl extends DAO<Grade> implements GradeDAO {
    private static final String START_GETTING_STUDENT_GRADES_BY_COURSE_LOG_MESSAGE
            = "Table: 'grades', start getting grades with student = {} and course = {}";
    private static final Logger LOGGER = LoggerFactory.getLogger(GradeDAOImpl.class);
    private final EntityManager em;

    public GradeDAOImpl() {
        super(Grade.class, LOGGER);
        this.em = super.getEntityManager();
    }


    @Override
    public Set<Grade> getStudentsGradesByCourse(Course course, Student student) {
        LOGGER.info(START_GETTING_STUDENT_GRADES_BY_COURSE_LOG_MESSAGE, student, course);

        return ExecutorUtils.executeHibernate(this.em, em -> {
            Set<Grade> grades = student.getGrades();
            if (grades != null) {
                return grades.stream()
                        .filter(grade -> grade.getTask().getCourse().equals(course))
                        .collect(Collectors.toSet());
            }

            return null;
        });
    }
}
