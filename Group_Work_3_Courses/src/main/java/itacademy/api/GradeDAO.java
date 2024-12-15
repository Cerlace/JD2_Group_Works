package itacademy.api;

import itacademy.entity.Course;
import itacademy.entity.Grade;
import itacademy.entity.Student;

import java.util.Set;

public interface GradeDAO extends IDAO<Grade> {
    Set<Grade> getStudentsGradesByCourse(Course course, Student student);
}
