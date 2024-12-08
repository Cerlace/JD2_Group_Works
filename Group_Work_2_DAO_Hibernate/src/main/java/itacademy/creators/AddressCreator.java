package itacademy.creators;

import itacademy.api.Creator;
import itacademy.entity.Address;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.ConsoleUtils;
import org.slf4j.Logger;

public class AddressCreator implements Creator<Address> {
    private static final String REQUEST_STREET_ENTRY = "Введите название улицы: ";
    private static final String REQUEST_HOUSE_ENTRY = "Введите номер дома: ";
    private final Logger logger;

    public AddressCreator(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Address create() throws InvalidInputException {
        this.logger.debug(REQUEST_STREET_ENTRY);
        ConsoleUtils.inputString();
        String street = ConsoleUtils.inputString();
        this.logger.debug(REQUEST_HOUSE_ENTRY);
        int house = ConsoleUtils.inputInt();

        return Address.builder()
                .street(street)
                .house(house)
                .build();
    }
}
