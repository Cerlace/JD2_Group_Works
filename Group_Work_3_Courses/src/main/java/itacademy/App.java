package itacademy;


import itacademy.entity.Student;
import itacademy.entity.Teacher;
import itacademy.entity.Grade;
import itacademy.entity.Task;
import itacademy.entity.Course;
import itacademy.entity.embedded.PersonData;
import itacademy.utils.HibernateUtils;

import javax.persistence.EntityManager;
import java.util.Set;

public class App {

    public static final int COURSE_DURATION = 198;

    public static void main(String[] args) {
        Student student = Student.builder()
                .personData(PersonData.builder()
                        .name("Student Name")
                        .surname("Student Surname")
                        .patronymic("Student Patronymic")
                        .email("student@mail.ru")
                        .build())
                .build();

        Teacher teacher = Teacher.builder()
                .personData(PersonData.builder()
                        .name("Teacher Name")
                        .surname("Teacher Surname")
                        .patronymic("Teacher Patronymic")
                        .email("teacher@mail.ru")
                        .build())
                .build();

        Course course = Course.builder()
                .name("Java")
                .duration(COURSE_DURATION)
                .teacher(teacher)
                .students(Set.of(student))
                .build();

        student.setCourses(Set.of(course));

        Task task = Task.builder()
                .course(course)
                .title("Homework")
                .description("GroupWork Hibernate")
                .build();

        course.setTasks(Set.of(task));

        Grade grade = Grade.builder()
                .task(task)
                .grade("9")
                .feedback("Good")
                .student(student)
                .build();

        EntityManager em = HibernateUtils.getEntityManager();

        em.getTransaction().begin();
        em.persist(student);
        em.persist(teacher);
        em.persist(course);
        em.persist(grade);
        em.getTransaction().commit();
    }
}
