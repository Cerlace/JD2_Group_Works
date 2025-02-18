package itacademy.service.impl;

import itacademy.dto.EngineDto;
import itacademy.entity.EngineEntity;
import itacademy.repository.EngineRepository;
import itacademy.service.api.EngineService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EngineServiceImpl implements EngineService {
    private final ConversionService conversionService;
    private final EngineRepository engineRepository;

    @Override
    public EngineDto saveOrUpdateEngine(EngineDto engineDto) {
        EngineEntity entity = conversionService.convert(engineDto, EngineEntity.class);
        if (entity != null) {
            return conversionService.convert(engineRepository.save(entity), EngineDto.class);
        } else return null;
    }

    @Override
    public void deleteEngine(Long id) {
        engineRepository.deleteById(id);
    }

    @Override
    public EngineDto getEngine(Long id) {
        Optional<EngineEntity> engineEntity = engineRepository.findById(id);
        return engineEntity.map(entity -> conversionService.convert(entity, EngineDto.class))
                .orElse(null);
    }

    @Override
    public Page<EngineDto> getAllEngines(Pageable pageable) {
        return engineRepository.findAll(pageable)
                .map(entity -> conversionService.convert(entity, EngineDto.class));
    }

    @Override
    public Page<EngineDto> getEnginesByHorsePower(Integer horsePower, Pageable pageable) {
        return engineRepository.findByHorsePowerGreaterThan(horsePower, pageable)
                .map(entity -> conversionService.convert(entity, EngineDto.class));
    }
}
