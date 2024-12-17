package itacademy.api;

import itacademy.entity.Course;
import itacademy.entity.Task;

import java.util.Date;
import java.util.List;


public interface TaskDAO extends IDAO<Task> {
    /**
     * Выбирает новейшую задачу {@code Task}, из определенного курса {@code Course}
     *
     * @param course курс, из которого выбирается задача
     * @return задача {@code Task}, соответствующая условию
     */
    Task getNewestTaskByCourse(Course course);

    /**
     * Выбирает все задачи {@code Task}, заданные раньше определенной даты
     *
     * @param date дата для выбора задач
     * @return список задач {@code List<Task>}, соответствующих условию
     */
    List<Task> getTasksEarlierThan(Date date);
}
