package itacademy.api;

import itacademy.entity.Course;

import java.util.Map;

public interface CourseDAO extends IDAO<Course> {
    /**
     * Выбирает курс {@code Course} с самым высоким средним баллом {@code Grade},
     * полученным за задания {@code Task}
     *
     * @return курс {@code Course}, соответствующий условию
     */
    Course getCourseWithGreatestAvgGrades();

    /**
     * Выбирает все курсы {@code Course} и подсчитывает количество учеников {@code Student}, записанных на них
     *
     * @return список ключ-значений {@code Map<Course, Integer>} с курсом и количеством учеников {@code Student}
     */
    Map<Course, Integer> getCoursesAndStudentsCount();
}
