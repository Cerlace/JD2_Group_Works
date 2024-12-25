package itacademy.dto;

import itacademy.utils.CarConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CarDTO extends CarConverter {
    /**
     * Поля CarDTO копируются из класса CarConverter
     */
}
