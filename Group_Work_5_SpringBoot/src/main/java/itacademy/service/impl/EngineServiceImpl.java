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

@Service
@Transactional
@RequiredArgsConstructor
public class EngineServiceImpl implements EngineService {
    private final ConversionService conversionService;
    private final EngineRepository engineRepository;

    @Override
    public void addEngine(EngineDto engineDto) {
        EngineEntity entity = conversionService.convert(engineDto, EngineEntity.class);
        if (entity != null) {
            engineRepository.save(entity);
        }
    }

    @Override
    public void updateEngine(EngineDto engineDto) {
        EngineEntity newEntity = conversionService.convert(engineDto, EngineEntity.class);
        if (newEntity != null) {
            engineRepository.findById(engineDto.getId())
                    .ifPresent(oldEntity -> newEntity.setCars(oldEntity.getCars()));
            engineRepository.save(newEntity);
        }
    }

    @Override
    public void deleteEngine(Long id) {
        engineRepository.deleteById(id);
    }

    @Override
    public EngineDto getEngine(Long id) {
        return conversionService.convert(engineRepository.findById(id), EngineDto.class);
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
