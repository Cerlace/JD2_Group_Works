package itacademy.service.impl;

import itacademy.dto.CarDto;
import itacademy.service.api.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static itacademy.TestConstants.TEST_CAR;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class CarServiceImplTest {
    @Autowired
    private CarService carService;

    @Test
    void saveCar() {
        CarDto savedCar = carService.saveOrUpdateCar(TEST_CAR);
        assertNotNull(savedCar.getId());
        assertEquals(TEST_CAR.getModel(), savedCar.getModel());
    }

    @Test
    void updateCar() {
        TEST_CAR.setId(1L);
        CarDto updatedCar = carService.saveOrUpdateCar(TEST_CAR);
        assertEquals(1L, updatedCar.getId());
        assertEquals(TEST_CAR.getModel(), updatedCar.getModel());
        TEST_CAR.setId(null);
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
        Page<CarDto> page2 = carService.getAllCars(1, 4);
        assertEquals(6, page2.getTotalElements());
        assertEquals(2, page2.getTotalPages());
        assertEquals(1, page2.getNumber());
        assertEquals(2, page2.getNumberOfElements());
    }

    @Test
    void getCarsByEngineId() {
        carService.getCarsByEngineId(1L, 0, 6)
                .forEach(carDto ->
                        assertEquals(1L, carDto.getEngine().getId()));
    }

    @Test
    void getCarsByBrand() {
        carService.getCarsByBrand("Ferrari", 0, 6)
                .forEach(carDto ->
                        assertEquals("Ferrari", carDto.getBrand()));
    }
}