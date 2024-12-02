package itacademy.creators;

import itacademy.api.Creator;
import itacademy.entity.People;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.ConsoleUtils;
import itacademy.utils.DataOutputUtils;

public class PeopleCreator implements Creator<People> {
    public static final String REQUEST_NAME_ENTRY = "Введите имя: ";
    public static final String REQUEST_SURNAME_ENTRY = "Введите фамилию: ";
    public static final String REQUEST_AGE_ENTRY = "Введите возраст: ";

    @Override
    public People create() throws InvalidInputException {
        DataOutputUtils.displayMessage(REQUEST_NAME_ENTRY);
        ConsoleUtils.inputString();
        String name = ConsoleUtils.inputString();
        DataOutputUtils.displayMessage(REQUEST_SURNAME_ENTRY);
        String surname = ConsoleUtils.inputString();
        DataOutputUtils.displayMessage(REQUEST_AGE_ENTRY);
        int age = ConsoleUtils.inputInt();

        return People.builder()
                .name(name)
                .surname(surname)
                .age(age)
                .build();
    }
}
