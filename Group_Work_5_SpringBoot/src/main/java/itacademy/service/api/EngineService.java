package itacademy.service.api;

import itacademy.dto.EngineDto;
import org.springframework.data.domain.Page;

public interface EngineService {
    EngineDto saveOrUpdateEngine(EngineDto engineDto);
    void deleteEngine(Long id);
    EngineDto getEngine(Long id);
    Page<EngineDto> getEnginesByHorsePower(Integer horsePower, int page, int size);
}
