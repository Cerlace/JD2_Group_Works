package itacademy.creators;

import itacademy.api.Creator;
import itacademy.exceptions.checked.InvalidInputException;
import itacademy.utils.ConsoleUtils;
import org.slf4j.Logger;

import java.io.Serializable;

public class IdCreator implements Creator<Serializable> {
    private static final String REQUEST_ID_ENTRY = "Введите id: ";
    private final Logger logger;

    public IdCreator(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Serializable create() throws InvalidInputException {
        this.logger.debug(REQUEST_ID_ENTRY);
        return ConsoleUtils.inputInt();
    }
}
