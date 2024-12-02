package itacademy.creators;

import itacademy.api.Creator;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.ConsoleUtils;
import itacademy.utils.DataOutputUtils;

import java.io.Serializable;

public class IdCreator implements Creator<Serializable> {
    public static final String REQUEST_ID_ENTRY = "Введите id: ";

    @Override
    public Serializable create() throws InvalidInputException {
        DataOutputUtils.displayMessage(REQUEST_ID_ENTRY);
        return ConsoleUtils.inputInt();
    }
}
