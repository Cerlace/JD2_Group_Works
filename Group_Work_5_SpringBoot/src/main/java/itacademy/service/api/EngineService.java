package itacademy.service.api;

import itacademy.dto.EngineDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EngineService {
    void addEngine(EngineDto engineDto);
    void updateEngine(EngineDto engineDto);
    void deleteEngine(Long id);
    EngineDto getEngine(Long id);
    Page<EngineDto> getAllEngines(Pageable pageable);
    Page<EngineDto> getEnginesByHorsePower(Integer horsePower, Pageable pageable);
}
