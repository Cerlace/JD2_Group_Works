package itacademy.api;

import itacademy.entity.Student;

import java.util.Set;

public interface StudentDAO extends IDAO<Student> {
    Set<Student> getStudentsByMinAvgGrade(Integer minAvgGrade);
}
