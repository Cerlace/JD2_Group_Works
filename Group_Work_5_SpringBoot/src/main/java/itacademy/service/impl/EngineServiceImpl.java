package itacademy.service.impl;

import itacademy.dto.EngineDto;
import itacademy.entity.EngineEntity;
import itacademy.repository.EngineRepository;
import itacademy.service.api.EngineService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<EngineDto> findAll() {
        return engineRepository.findAll()
                .stream()
                .map(entity -> conversionService.convert(entity, EngineDto.class))
                .collect(Collectors.toList());
    }
  
    @Override
    public Page<EngineDto> getEnginesByHorsePower(Integer horsePower, int page, int size) {
        return engineRepository.findByHorsePowerGreaterThan(horsePower, PageRequest.of(page, size))
                .map(entity -> conversionService.convert(entity, EngineDto.class));
    }
}
