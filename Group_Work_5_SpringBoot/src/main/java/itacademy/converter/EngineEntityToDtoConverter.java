package itacademy.converter;

import itacademy.dto.EngineDto;
import itacademy.entity.EngineEntity;
import org.springframework.core.convert.converter.Converter;

public class EngineEntityToDtoConverter implements Converter<EngineEntity, EngineDto> {
    @Override
    public EngineDto convert(EngineEntity source) {
        return EngineDto.builder()
                .id(source.getId())
                .model(source.getModel())
                .horsePower(source.getHorsePower())
                .build();
    }
}
