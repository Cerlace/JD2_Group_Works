package itacademy.converter;

import itacademy.dto.CarDto;
import itacademy.entity.CarEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CarEntityToDtoConverter implements Converter<CarEntity, CarDto> {
    @Override
    public CarDto convert(CarEntity source) {
        return CarDto.builder()
                .id(source.getId())
                .brand(source.getModel())
                .engineId(source.getEngine().getId())
                .build();
    }
}
