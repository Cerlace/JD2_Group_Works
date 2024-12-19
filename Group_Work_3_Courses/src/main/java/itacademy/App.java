package itacademy;

import itacademy.api.*;
import itacademy.dao.impl.*;
import itacademy.entity.*;

public class App {
    public static void main(String[] args) throws IllegalAccessException {
        StudentDAO studentDAO = new StudentDAOImpl();
        TeacherDAO teacherDAO = new TeacherDAOImpl();
        CourseDAO courseDAO = new CourseDAOImpl();
        GradeDAO gradeDAO = new GradeDAOImpl();
        TaskDAO taskDAO = new TaskDAOImpl();

//НЕ СРАБОТАЕТ
        Task task = taskDAO.get(1);
        task.getGrades().add(gradeDAO.get(9));
        task.getGrades().add(gradeDAO.get(10));
        taskDAO.update(1, task);
//СРАБОТАЕТ
        Grade grade1 = gradeDAO.get(9);
        Grade grade2 = gradeDAO.get(10);
        grade1.setTask(taskDAO.get(1));
        grade2.setTask(taskDAO.get(1));
        gradeDAO.update(9, grade1);
        gradeDAO.update(10, grade2);
    }
}
