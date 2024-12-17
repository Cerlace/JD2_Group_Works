package itacademy.api;

import itacademy.entity.Course;
import itacademy.entity.Grade;
import itacademy.entity.Student;

import java.util.Set;

public interface GradeDAO extends IDAO<Grade> {
    /**
     * Выбирает все оценки {@code Grade} студента, полученные за курс
     * @param course курс, за который выставлены оценки
     * @param student студент, оценки которого необходимо получить
     * @return множество оценок, соответствующих условию
     */
    Set<Grade> getStudentsGradesByCourse(Course course, Student student);
}
