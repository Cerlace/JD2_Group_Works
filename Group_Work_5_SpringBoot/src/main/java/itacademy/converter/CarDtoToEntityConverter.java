package itacademy.converter;

import itacademy.dto.CarDto;
import itacademy.dto.EngineDto;
import itacademy.entity.CarEntity;
import itacademy.entity.EngineEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarDtoToEntityConverter implements Converter<CarDto, CarEntity> {

    private final EngineDtoToEntityConverter converter;

    @Override
    public CarEntity convert(CarDto source) {
        return CarEntity.builder()
                .id(source.getId())
                .brand(source.getBrand())
                .model(source.getModel())
                .engine(converter.convert(source.getEngine()))
                .build();
    }
}
