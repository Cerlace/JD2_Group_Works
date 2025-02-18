package itacademy.converter;

import itacademy.dto.CarDto;
import itacademy.dto.EngineDto;
import itacademy.entity.CarEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarEntityToDtoConverter implements Converter<CarEntity, CarDto> {

    private final EngineEntityToDtoConverter converter;

    @Override
    public CarDto convert(CarEntity source) {
        return CarDto.builder()
                .id(source.getId())
                .brand(source.getBrand())
                .model(source.getModel())
                .engine(converter.convert(source.getEngine()))
                .build();
    }
}
