package itacademy.service.impl;

import itacademy.dto.CarDto;
import itacademy.entity.CarEntity;
import itacademy.entity.EngineEntity;
import itacademy.repository.CarRepository;
import itacademy.repository.EngineRepository;
import itacademy.service.api.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final EngineRepository engineRepository;
    private final ConversionService conversionService;

    @Override
    public void saveCar(CarDto carDto) {
        EngineEntity engineEntity = engineRepository.findById(carDto.getEngineId()).orElse(null);
        if (engineEntity != null) {
            CarEntity carEntity = conversionService.convert(carDto, CarEntity.class);
            carEntity.setEngine(engineEntity);
            engineEntity.getCars().add(carEntity);
            carRepository.save(carEntity);
        }
    }

    @Override
    public void updateCar(CarDto carDto) {
        EngineEntity engineEntity = engineRepository.findById(carDto.getEngineId()).orElse(null);
        if (engineEntity != null) {
            CarEntity carEntity = conversionService.convert(carDto, CarEntity.class);
            carEntity.setEngine(engineEntity);
            carRepository.save(carEntity);
        }
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public CarDto getCar(Long id) {
        return conversionService.convert(carRepository.findById(id), CarDto.class);
    }

    @Override
    public Page<CarDto> getAllCars(Pageable pageable) {
        return carRepository.findAll(pageable)
                .map(entity -> conversionService.convert(entity, CarDto.class));
    }

    @Override
    public Page<CarDto> getCarsByEngineId(Long engineId, Pageable pageable) {
        return carRepository.findByEngineId(engineId, pageable)
                .map(entity -> conversionService.convert(entity, CarDto.class));
    }

    @Override
    public Page<CarDto> getCarsByBrand(String brand, Pageable pageable) {
        return carRepository.findByBrand(brand, pageable)
                .map(entity -> conversionService.convert(entity, CarDto.class));
    }
}
