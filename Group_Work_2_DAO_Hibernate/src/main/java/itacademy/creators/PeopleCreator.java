package itacademy.creators;

import itacademy.api.Creator;
import itacademy.entity.People;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.ConsoleUtils;
import org.slf4j.Logger;

public class PeopleCreator implements Creator<People> {
    public static final String REQUEST_NAME_ENTRY = "Введите имя: ";
    public static final String REQUEST_SURNAME_ENTRY = "Введите фамилию: ";
    public static final String REQUEST_AGE_ENTRY = "Введите возраст: ";

    private final Logger logger;

    public PeopleCreator(Logger logger) {
        this.logger = logger;
    }

    @Override
    public People create() throws InvalidInputException {
        this.logger.debug(REQUEST_NAME_ENTRY);
        ConsoleUtils.inputString();
        String name = ConsoleUtils.inputString();
        this.logger.debug(REQUEST_SURNAME_ENTRY);
        String surname = ConsoleUtils.inputString();
        this.logger.debug(REQUEST_AGE_ENTRY);
        int age = ConsoleUtils.inputInt();

        return People.builder()
                .name(name)
                .surname(surname)
                .age(age)
                .build();
    }
}
