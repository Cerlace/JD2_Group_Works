package itacademy.utils;

import lombok.Data;

@Data
public abstract class CarConverter {
    /**
     * Данные поля наследуются классами CarDTO и CarEntity
     */
    private Integer id;
    private String vin;
    private String name;
}
