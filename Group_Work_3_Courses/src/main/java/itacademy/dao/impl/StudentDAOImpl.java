package itacademy.dao.impl;

import itacademy.api.StudentDAO;
import itacademy.dao.DAO;
import itacademy.entity.Student;
import itacademy.utils.ExecutorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;

public class StudentDAOImpl extends DAO<Student> implements StudentDAO {
    private static final String GET_SUDENTS_BY_MIN_AVG_GRADE_LOG_MESSAGE =
            "Table: 'students', start getting students where average grade more than {}";
    private static final Logger LOGGER =
            LoggerFactory.getLogger(StudentDAOImpl.class);

    private static final String QUERY_FOR_GET_STUDENTS_BY_AVG_GRADE =
            "select g.student " +
            "from Grade g group by g.student " +
            "having avg (g.score) >= :minAvgGrade";

    private final EntityManager em;

    public StudentDAOImpl() {
        super(Student.class, LOGGER);
        this.em = super.getEntityManager();
    }

    @Override
    public Set<Student> getStudentsByMinAvgGrade(Integer minAvgGrade) {
        LOGGER.info(GET_SUDENTS_BY_MIN_AVG_GRADE_LOG_MESSAGE, minAvgGrade);

        return ExecutorUtils.executeHibernate(this.em, em ->
                new HashSet<>(em.createQuery(QUERY_FOR_GET_STUDENTS_BY_AVG_GRADE)
                        .setParameter("minAvgGrade", (double)minAvgGrade)
                        .getResultList()));
    }
}
