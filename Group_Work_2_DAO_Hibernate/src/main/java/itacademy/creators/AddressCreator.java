package itacademy.creators;

import itacademy.api.Creator;
import itacademy.entity.Address;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.ConsoleUtils;
import itacademy.utils.DataOutputUtils;

public class AddressCreator implements Creator<Address> {
    private static final String REQUEST_STREET_ENTRY = "Введите название улицы: ";
    private static final String REQUEST_HOUSE_ENTRY = "Введите номер дома: ";

    @Override
    public Address create() throws InvalidInputException {
        DataOutputUtils.displayMessage(REQUEST_STREET_ENTRY);
        ConsoleUtils.inputString();
        String street = ConsoleUtils.inputString();
        DataOutputUtils.displayMessage(REQUEST_HOUSE_ENTRY);
        int house = ConsoleUtils.inputInt();

        return Address.builder()
                .street(street)
                .house(house)
                .build();
    }
}
