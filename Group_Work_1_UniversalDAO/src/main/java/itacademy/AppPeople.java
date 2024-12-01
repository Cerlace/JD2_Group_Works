package itacademy;

import itacademy.dao.impl.PeopleDAOImpl;
import itacademy.dto.People;

import java.util.List;

public class AppPeople {
    public static void main(String[] args) {
        PeopleDAOImpl peopleDAO = new PeopleDAOImpl();

        People people1 = People.builder()
                .name("Иван")
                .surname("Джавинский")
                .age(37)
                .build();

        People people2 = People.builder()
                .name("Антон")
                .surname("Левский")
                .age(43)
                .build();

        People people3 = People.builder()
                .name("Алина")
                .surname("Ремесленникова")
                .age(21)
                .build();

        People people4 = People.builder()
                .name("Елена")
                .surname("Простакова")
                .age(25)
                .build();

        People people5 = People.builder()
                .name("Владислав")
                .surname("Громов")
                .age(34)
                .build();

        peopleDAO.save(people1);
        peopleDAO.save(people2);
        peopleDAO.save(people3);
        peopleDAO.save(people4);
        peopleDAO.save(people5);

        System.out.println("метод getById");
        System.out.println(peopleDAO.getById(2));


        System.out.println("метод getAll");
        List<People> peopleList = peopleDAO.getAll();
        peopleList.forEach(System.out::println);

        System.out.println("метод update");
        people4.setSurname("Новицкая");
        peopleDAO.update(4, people4);

        System.out.println("метод delete");
        peopleDAO.delete(3);

        System.out.println("метод getAll");
        List<People> peopleList2 = peopleDAO.getAll();
        peopleList2.forEach(System.out::println);

        peopleDAO.closeEMF();
    }
}
