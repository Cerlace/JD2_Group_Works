package itacademy.exceptions;

import java.sql.SQLException;

/**
 * Исключение, которое выбрасывается, когда в базе данных
 * отсутствует запрашиваемая запись.
 */
public class RecordNotFoundException extends SQLException {

    /**
     * Создает новое исключение {@code RecordNotFound}
     * с заданным сообщением.
     *
     * @param message сообщение, описывающее причину исключения
     */
    public RecordNotFoundException(String message) {
        super(message);
    }
}
