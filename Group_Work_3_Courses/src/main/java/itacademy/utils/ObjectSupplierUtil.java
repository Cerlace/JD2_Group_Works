package itacademy.utils;


import itacademy.entity.Grade;
import itacademy.entity.Course;
import itacademy.entity.Student;
import itacademy.entity.Task;
import itacademy.entity.Teacher;
import itacademy.entity.embedded.PersonData;

public class ObjectSupplierUtil {

    public static final int DEFAULT_COURSE_DURATION = 50;

    public static Student getStudent(int suffix) {
        return Student.builder()
                .personData(PersonData.builder()
                        .surname("Student Surname " + suffix)
                        .name("Student Name " + suffix)
                        .patronymic("Student Patronymic " + suffix)
                        .email("Student email " + suffix)
                        .build())
                .build();
    }

    public static Teacher getTeacher(int suffix) {
        return Teacher.builder()
                .personData(PersonData.builder()
                        .surname("Teacher Name " + suffix)
                        .name("Teacher Name " + suffix)
                        .patronymic("Teacher Patronymic " + suffix)
                        .email("Teacher email " + suffix)
                        .build())
                .build();
    }

    public static Course getCourse(int suffix) {
        return Course.builder()
                .name("Course " + suffix)
                .duration(DEFAULT_COURSE_DURATION * suffix)
                .build();
    }

    public static Task getTask(int suffix) {
        return Task.builder()
                .title("Task " + suffix)
                .description("Task description " + suffix)
                .build();
    }

    public static Grade getGrade(int score) {
        return Grade.builder()
                .feedback("Feedback for task")
                .score(score)
                .build();
    }
}
