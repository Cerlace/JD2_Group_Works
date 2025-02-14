package itacademy.converter;

import itacademy.dto.CarDto;
import itacademy.entity.CarEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CarDtoToEntityConverter implements Converter<CarDto, CarEntity> {
    @Override
    public CarEntity convert(CarDto source) {
        return CarEntity.builder()
                .id(source.getId())
                .brand(source.getBrand())
                .model(source.getModel())
                .build();
    }
}
