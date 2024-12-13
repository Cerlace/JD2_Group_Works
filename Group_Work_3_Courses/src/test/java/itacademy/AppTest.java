package itacademy;

import itacademy.entity.Student;
import itacademy.entity.embedded.PersonData;
import itacademy.utils.HibernateUtils;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void databaseTest() {
        Student student = Student.builder()
                .personData(PersonData.builder()
                        .name("Student Name")
                        .surname("Student Surname")
                        .patronymic("Student Patronymic")
                        .email("student@mail.ru")
                        .build())
                .build();
        EntityManager manager = HibernateUtils.getEntityManager();

        manager.getTransaction().begin();
        manager.persist(student);
        manager.getTransaction().commit();
        manager.close();

        assertNotNull(student.getId());
    }
}