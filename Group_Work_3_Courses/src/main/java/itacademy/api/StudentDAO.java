package itacademy.api;

import itacademy.entity.Student;

import java.util.Set;

public interface StudentDAO extends IDAO<Student> {
    /**
     * Выбирает всех студентов, средняя оценка которых больше или равна
     * заданному значению
     * @param minAvgGrade минимальная средняя оценка для выбора
     * @return множество студентов, соответствующих условию
     */
    Set<Student> getStudentsByMinAvgGrade(Integer minAvgGrade);
}
