package itacademy.utils;

import itacademy.dto.CarDTO;
import itacademy.entity.CarEntity;

public class ConverterUtil {

    /**
     * Метод конвертирует {@code CarEntity} в {@code CarDTO}
     * @param entity входной объект типа {@code CarEntity}
     * @return объект типа {@code CarDTO}
     */
    public static CarDTO convertCar(CarEntity entity) {
        if (entity == null) {
            return null;
        }
        return CarDTO.builder()
                .id(entity.getId())
                .vin(entity.getVin())
                .name(entity.getName())
                .changeTime(entity.getChangeTime())
                .creationTime(entity.getCreationTime())
                .build();
    }

    /**
     * Метод конвертирует {@code CarDTO} в {@code CarEntity}
     * @param dto входной объект типа {@code CarDTO}
     * @return объект типа {@code CarEntity}
     */
    public static CarEntity convertCar(CarDTO dto) {
        if (dto == null) {
            return null;
        }
        return CarEntity.builder()
                .id(dto.getId())
                .vin(dto.getVin())
                .name(dto.getName())
                .changeTime(dto.getChangeTime())
                .creationTime(dto.getCreationTime())
                .build();
    }
}
