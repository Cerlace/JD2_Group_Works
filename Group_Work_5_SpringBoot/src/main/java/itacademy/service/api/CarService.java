package itacademy.service.api;

import itacademy.dto.CarDto;
import itacademy.dto.CarFilterDto;
import org.springframework.data.domain.Page;

public interface CarService {
    CarDto saveOrUpdateCar(CarDto carDto);
    void deleteCar(Long id);
    CarDto getCar(Long id);
    Page<CarDto> getCarsByFilter(CarFilterDto filter);
}
