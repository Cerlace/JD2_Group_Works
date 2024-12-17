package itacademy;


import itacademy.api.CourseDAO;
import itacademy.api.GradeDAO;
import itacademy.api.StudentDAO;
import itacademy.api.TaskDAO;
import itacademy.api.TeacherDAO;
import itacademy.dao.impl.CourseDAOImpl;
import itacademy.dao.impl.GradeDAOImpl;
import itacademy.dao.impl.StudentDAOImpl;
import itacademy.dao.impl.TeacherDAOImpl;
import itacademy.dao.impl.TaskDAOImpl;
import itacademy.utils.HibernateUtils;

import java.util.Date;

public class GetApp {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAOImpl();
        TeacherDAO teacherDAO = new TeacherDAOImpl();
        CourseDAO courseDAO = new CourseDAOImpl();
        GradeDAO gradeDAO = new GradeDAOImpl();
        TaskDAO taskDAO = new TaskDAOImpl();

        studentDAO.getStudentsByMinAvgGrade(6).forEach(System.out::println);
        gradeDAO.getStudentsGradesByCourse(courseDAO.get(1), studentDAO.get(1)).forEach(System.out::println);

        System.out.println(teacherDAO.getTeacherWithGreatestTaskCount());
        teacherDAO.getTeachersWithLeastCoursesCount(2).forEach(System.out::println);

        System.out.println(courseDAO.getCourseWithGreatestAvgGrades());
        courseDAO.getCoursesAndStudentsCount().entrySet().forEach(entry ->
                System.out.println(entry.getKey().getId() + " " + entry.getKey().getName() +
                        " students count: " + entry.getValue()));

        System.out.println(taskDAO.getNewestTaskByCourse(courseDAO.get(1)));
        taskDAO.getTasksEarlierThan(new Date()).forEach(System.out::println);

        studentDAO.close();
        teacherDAO.close();
        courseDAO.close();
        gradeDAO.close();
        taskDAO.close();
        HibernateUtils.close();
    }
}
