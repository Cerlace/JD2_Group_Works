package itacademy.service.impl;

import itacademy.dto.CarDto;
import itacademy.dto.CarFilterDto;
import itacademy.entity.CarEntity;
import itacademy.repository.CarRepository;
import itacademy.service.api.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ConversionService conversionService;

    @Override
    public CarDto saveOrUpdateCar(CarDto carDto) {
        CarEntity carEntity = conversionService.convert(carDto, CarEntity.class);
        if (carEntity != null) {
            return conversionService.convert(carRepository.save(carEntity), CarDto.class);
        } else return null;
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public CarDto getCar(Long id) {
        Optional<CarEntity> carEntity = carRepository.findById(id);
        return carEntity.map(entity -> conversionService.convert(entity, CarDto.class))
                .orElse(null);

    }

    @Override
    public Page<CarDto> getCarsByFilter(CarFilterDto filter) {
        Page<CarEntity> carsPage;
        if (filter.getBrand() != null && !filter.getBrand().isBlank()) {
            carsPage = carRepository.findByBrand(filter.getBrand(),
                    PageRequest.of(filter.getPageNumber(), filter.getPageSize()));
        } else if (filter.getEngineId() != null) {
            carsPage = carRepository.findByEngineId(filter.getEngineId(),
                    PageRequest.of(filter.getPageNumber(), filter.getPageSize()));
        } else {
            carsPage = carRepository.findAll(
                    PageRequest.of(filter.getPageNumber(), filter.getPageSize()));
        }
        return carsPage.map(entity -> conversionService.convert(entity, CarDto.class));
    }
}
