package itacademy.utils;

import itacademy.dto.CarDTO;
import itacademy.entity.CarEntity;

import java.sql.Timestamp;

public class CarConverter {
    public static CarDTO toDTO(CarEntity carEntity) {
        if (carEntity == null) {
            return null;
        }
        return CarDTO.builder()
                .id(carEntity.getId())
                .vin(carEntity.getVin())
                .name(carEntity.getName())
                .changeTime(carEntity.getChangeTime())
                .build();
    }

    public static CarEntity toEntity(CarDTO carDTO) {
        if (carDTO == null) {
            return null;
        }
        return CarEntity.builder()
                .id(carDTO.getId())
                .vin(carDTO.getVin())
                .name(carDTO.getName())
                .changeTime(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
