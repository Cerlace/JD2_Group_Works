package itacademy.exceptions.unchecked;

/**
 * Исключение, которое выбрасывается, когда требуется аннотация,
 * отсутствует в классе или поле, где она должна быть.
 */
public class AnnotationMissingException extends RuntimeException {

    /**
     * Создает новое исключение {@code AnnotationMissingException} с заданным сообщением.
     * @param message сообщение, описывающее причину исключения
     */
    public AnnotationMissingException(String message) {
        super(message);
    }
}
