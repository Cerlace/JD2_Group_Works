package itacademy.utils;

import itacademy.dto.CarDTO;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;

public class ServletUtil {
    /**
     * Создает объект типа {@code CarDTO}, заполняя поля значениями из параметров,
     * полученных из объекта HttpServletRequest
     * @param req объект HttpServletRequest
     * @return объект типа {@code CarDTO}
     */
    public static CarDTO mapCar(HttpServletRequest req) {
        return CarDTO.builder()
                .name(getStringParam(req, ServletConstants.CAR_NAME_PARAMETER))
                .vin(getStringParam(req, ServletConstants.CAR_VIN_PARAMETER))
                .build();
    }
    /**
     * Получает из объекта HttpServletRequest параметр по его имени
     * @param req объект HttpServletRequest
     * @param nameField имя параметра, который получаем из HttpServletRequest
     * @return строковое представление параметра
     */
    public static String getStringParam(HttpServletRequest req, String nameField) {
        return Optional.ofNullable(req.getParameter(nameField))
                .filter(StringUtils::isNotBlank)
                .orElse(null);
    }

    /**
     * Получает из объекта HttpServletRequest параметр по его имени
     * @param req объект HttpServletRequest
     * @param nameField имя параметра, который получаем из HttpServletRequest
     * @return представление параметра типа {@code Integer}
     */
    public static Integer getIntegerParam(HttpServletRequest req, String nameField) {
        return Integer.parseInt(Objects.requireNonNull(req.getParameter(nameField)));
    }
}
