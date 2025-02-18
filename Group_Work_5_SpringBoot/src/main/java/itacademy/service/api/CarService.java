package itacademy.service.api;

import itacademy.dto.CarDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService {
    CarDto saveOrUpdateCar(CarDto carDto);
    void deleteCar(Long id);
    CarDto getCar(Long id);
    Page<CarDto> getAllCars(Pageable pageable);
    Page<CarDto> getCarsByEngineId(Long engineId, Pageable pageable);
    Page<CarDto> getCarsByBrand(String brand, Pageable pageable);
}
