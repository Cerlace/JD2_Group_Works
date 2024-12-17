package itacademy.api;

import itacademy.entity.Teacher;

import java.util.List;

public interface TeacherDAO extends IDAO<Teacher> {
    /**
     * Выбирает учителя {@code Teacher}, по курсам {@code Course}  которого задано больше
     * всего заданий {@code Task}
     *
     * @return учитель {@code Teacher}, соответствующий условию
     */
    Teacher getTeacherWithGreatestTaskCount();

    /**
     * Выбирает нескольких учителей {@code Teacher}, с самым низким
     * количеством курсов {@code Course}
     *
     * @param count количество учителей для выбора
     * @return список учителей {@code List<Teacher>}, по условию выбора
     */
    List<Teacher> getTeachersWithLeastCoursesCount(int count);
}
