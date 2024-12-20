package itacademy.service;

import itacademy.dto.CarDTO;

import java.io.Serializable;
import java.util.List;

public interface CarService {
    CarDTO save(CarDTO t);

    CarDTO getById(Serializable id);

    List<CarDTO> getAll();

    CarDTO update(Serializable id, CarDTO car) throws IllegalAccessException;

    boolean delete(Serializable id);
}
