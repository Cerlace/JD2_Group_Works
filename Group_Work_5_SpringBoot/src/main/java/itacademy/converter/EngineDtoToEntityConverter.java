package itacademy.converter;

import itacademy.dto.EngineDto;
import itacademy.entity.EngineEntity;
import org.springframework.core.convert.converter.Converter;

public class EngineDtoToEntityConverter implements Converter<EngineDto, EngineEntity> {
    @Override
    public EngineEntity convert(EngineDto source) {
        return EngineEntity.builder()
                .id(source.getId())
                .model(source.getModel())
                .horsePower(source.getHorsePower())
                .build();
    }
}
