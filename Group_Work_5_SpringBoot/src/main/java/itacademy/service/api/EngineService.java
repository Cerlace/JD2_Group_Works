package itacademy.service.api;

import itacademy.dto.EngineDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EngineService {
    EngineDto saveOrUpdateEngine(EngineDto engineDto);
    void deleteEngine(Long id);
    EngineDto getEngine(Long id);
    List<EngineDto> findAll();
    Page<EngineDto> getEnginesByHorsePower(Integer horsePower, int page, int size);
}
