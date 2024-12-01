package itacademy;

import itacademy.dao.impl.AddressDAOImpl;
import itacademy.dto.Address;

import java.util.List;

public class AppAddress {
    public static void main(String[] args) {
        AddressDAOImpl addressDAO = new AddressDAOImpl();

        Address address1 = Address.builder()
                .street("Жуковского")
                .house(17)
                .build();

        Address address2 = Address.builder()
                .street("Партизанский пр-т.")
                .house(84)
                .build();

        Address address3 = Address.builder()
                .street("Одоевского")
                .house(78)
                .build();

        Address address4 = Address.builder()
                .street("Рокоссовского")
                .house(65)
                .build();

        Address address5 = Address.builder()
                .street("Ангарская")
                .house(198)
                .build();

        addressDAO.save(address1);
        addressDAO.save(address2);
        addressDAO.save(address3);
        addressDAO.save(address4);
        addressDAO.save(address5);

        System.out.println("метод getById");
        System.out.println(addressDAO.getById(2));


        System.out.println("метод getAll");
        List<Address> addressList = addressDAO.getAll();
        addressList.forEach(System.out::println);

        System.out.println("метод update");
        address2.setHouse(92);
        address2.setStreet("Ташкентская");
        addressDAO.update(2, address2);

        System.out.println("метод delete");
        addressDAO.delete(4);

        System.out.println("метод getAll");
        List<Address> addressList2 = addressDAO.getAll();
        addressList2.forEach(System.out::println);

        addressDAO.closeEMF();
    }
}
