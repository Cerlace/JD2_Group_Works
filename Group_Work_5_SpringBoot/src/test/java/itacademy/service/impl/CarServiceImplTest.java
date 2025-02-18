package itacademy.service.impl;

import itacademy.dto.CarDto;
import itacademy.dto.EngineDto;
import itacademy.service.api.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class CarServiceImplTest {
    @Autowired
    private CarService carService;

    public final EngineDto testEngine = EngineDto.builder()
            .model("BMW Engine")
            .horsePower(400)
            .build();
    public final CarDto testCar = CarDto.builder()
            .brand("BMW")
            .model("X5")
            .engine(testEngine)
            .build();

    @Test
    void saveCar() {
        CarDto savedCar = carService.saveOrUpdateCar(testCar);
        assertNotNull(savedCar.getId());
        assertEquals(testCar.getModel(), savedCar.getModel());
    }

    @Test
    void updateCar() {
        testCar.setId(1L);
        CarDto updatedCar = carService.saveOrUpdateCar(testCar);
        assertEquals(1L, updatedCar.getId());
        assertEquals(testCar.getModel(), updatedCar.getModel());
    }

    @Test
    void deleteCar() {
        carService.deleteCar(1L);
        assertNull(carService.getCar(1L));
    }

    @Test
    void getCar() {
        assertNotNull(carService.getCar(1L));
        assertNull(carService.getCar(10L));
    }

    @Test
    void getAllCars() {
        Page<CarDto> page2 = carService.getAllCars(PageRequest.of(1, 4));
        assertEquals(6, page2.getTotalElements());
        assertEquals(2, page2.getTotalPages());
        assertEquals(1, page2.getNumber());
        assertEquals(2, page2.getNumberOfElements());
    }

    @Test
    void getCarsByEngineId() {
        carService.getCarsByEngineId(1L, PageRequest.of(0, 6))
                .forEach(carDto ->
                        assertEquals(1L, carDto.getEngine().getId()));
    }

    @Test
    void getCarsByBrand() {
        carService.getCarsByBrand("Ferrari", PageRequest.of(0, 6))
                .forEach(carDto ->
                        assertEquals("Ferrari", carDto.getBrand()));
    }
}