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
import itacademy.entity.Student;
import itacademy.entity.Teacher;
import itacademy.entity.Grade;
import itacademy.entity.Task;
import itacademy.entity.Course;
import itacademy.utils.HibernateUtils;
import itacademy.utils.ObjectSupplierUtil;

import java.util.Set;

public class App {

    public static final int COURSE_DURATION = 198;

    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAOImpl();
        TeacherDAO teacherDAO = new TeacherDAOImpl();
        CourseDAO courseDAO = new CourseDAOImpl();
        GradeDAO gradeDAO = new GradeDAOImpl();
        TaskDAO taskDAO = new TaskDAOImpl();

        Student student1 = ObjectSupplierUtil.getStudent(1);
        Student student2 = ObjectSupplierUtil.getStudent(2);

        Teacher teacher1 = ObjectSupplierUtil.getTeacher(1);
        Teacher teacher2 = ObjectSupplierUtil.getTeacher(2);

        Course course1 = ObjectSupplierUtil.getCourse(1);
        course1.setTeacher(teacher1);
        course1.setStudents(Set.of(student1, student2));
        teacher1.setCourses(Set.of(course1));

        Course course2 = ObjectSupplierUtil.getCourse(2);
        course2.setTeacher(teacher2);
        course2.setStudents(Set.of(student1, student2));
        teacher2.setCourses(Set.of(course2));

        student1.setCourses(Set.of(course1, course2));
        student2.setCourses(Set.of(course1, course2));

        Task task1 = ObjectSupplierUtil.getTask(1);
        Task task2 = ObjectSupplierUtil.getTask(2);
        Task task3 = ObjectSupplierUtil.getTask(3);
        Task task4 = ObjectSupplierUtil.getTask(4);

        course1.setTasks(Set.of(task1, task2));
        course2.setTasks(Set.of(task3, task4));

        task1.setCourse(course1);
        task2.setCourse(course1);
        task3.setCourse(course2);
        task4.setCourse(course2);

        Grade grade1 = ObjectSupplierUtil.getGrade(10);
        Grade grade2 = ObjectSupplierUtil.getGrade(8);
        Grade grade3 = ObjectSupplierUtil.getGrade(6);
        Grade grade4 = ObjectSupplierUtil.getGrade(4);

        student1.setGrades(Set.of(grade1, grade2));
        student2.setGrades(Set.of(grade3, grade4));

        grade1.setStudent(student1);
        grade2.setStudent(student1);
        grade3.setStudent(student2);
        grade4.setStudent(student2);

        grade1.setTask(task1);
        grade2.setTask(task2);
        grade3.setTask(task3);
        grade4.setTask(task4);

        teacherDAO.save(teacher1);
        teacherDAO.save(teacher2);

        courseDAO.save(course1);
        courseDAO.save(course2);

        studentDAO.save(student1);
        studentDAO.save(student2);

        Set<Student> students = studentDAO.getStudentsByMinAvgGrade(3);
        students.forEach(System.out::println);

        Set<Grade> student1Grades = gradeDAO.getStudentsGradesByCourse(course1, student1);
        student1Grades.forEach(System.out::println);

        studentDAO.delete(student1.getId());

        Course course = courseDAO.get(course1.getId());
        course.getStudents().forEach(System.out::println);

        studentDAO.close();
        teacherDAO.close();
        courseDAO.close();
        gradeDAO.close();
        taskDAO.close();
        HibernateUtils.close();
    }
}
