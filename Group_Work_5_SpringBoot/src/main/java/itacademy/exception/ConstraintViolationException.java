package itacademy.exception;

import lombok.Getter;

@Getter
public class ConstraintViolationException extends RuntimeException {

    private final String SQLState;
    private final int errorCode;

    public ConstraintViolationException(String message, String SQLState, int errorCode) {
        super(message);
        this.SQLState = SQLState;
        this.errorCode = errorCode;
    }
}
