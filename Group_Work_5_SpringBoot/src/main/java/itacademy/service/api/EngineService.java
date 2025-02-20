package itacademy.service.api;

import itacademy.dto.EngineDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EngineService {
    EngineDto saveOrUpdateEngine(EngineDto engineDto);
    void deleteEngine(Long id);
    EngineDto getEngine(Long id);
    List<EngineDto> findAll();
    Page<EngineDto> getAllEngines(Pageable pageable);
    Page<EngineDto> getEnginesByHorsePower(Integer horsePower, Pageable pageable);
}
