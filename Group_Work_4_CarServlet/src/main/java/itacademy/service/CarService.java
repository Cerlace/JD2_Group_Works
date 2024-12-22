package itacademy.service;

import itacademy.dto.CarDTO;

import java.io.Serializable;
import java.util.List;

public interface CarService {
    CarDTO save(CarDTO t);

    List<CarDTO> getAll();

    CarDTO update(Serializable id, CarDTO car);

    boolean delete(String id);

    void closeDao();
}
