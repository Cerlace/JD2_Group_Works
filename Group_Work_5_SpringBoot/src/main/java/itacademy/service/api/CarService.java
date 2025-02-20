package itacademy.service.api;

import itacademy.dto.CarDto;
import org.springframework.data.domain.Page;

public interface CarService {
    CarDto saveOrUpdateCar(CarDto carDto);
    void deleteCar(Long id);
    CarDto getCar(Long id);
    Page<CarDto> getAllCars(int pageNumber, int pageSize);
    Page<CarDto> getCarsByEngineId(Long engineId, int pageNumber, int pageSize);
    Page<CarDto> getCarsByBrand(String brand, int pageNumber, int pageSize);
}
