package itacademy.controlleradvice;

import itacademy.exception.ConstraintViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String FK_CONSTRAINT_VIOLATION_PARENT_MESSAGE =
            "FK constraint violation! Cannot delete or update a parent row";
    public static final String UNKNOWN_CONSTRAINT_VIOLATION_MESSAGE =
            "Unknown constraint violation!";
    public static final int MY_SQL_FK_PARENT_ERROR = 1451;

    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolation(ConstraintViolationException exception, RedirectAttributes attributes) {
        attributes.addFlashAttribute("errorMessage", getErrorMessage(exception.getErrorCode()));

        return "redirect:/engines";
    }

    private static String getErrorMessage(int errorCode) {
        switch (errorCode) {
            case MY_SQL_FK_PARENT_ERROR -> {
                return FK_CONSTRAINT_VIOLATION_PARENT_MESSAGE;
            }
            default -> {
                return UNKNOWN_CONSTRAINT_VIOLATION_MESSAGE;
            }
        }
    }
}