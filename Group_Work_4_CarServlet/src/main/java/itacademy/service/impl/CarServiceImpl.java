package itacademy.service.impl;

import itacademy.dao.CarDAO;
import itacademy.dto.CarDTO;
import itacademy.service.CarService;

import java.io.Serializable;
import java.util.List;

public class CarServiceImpl implements CarService {

    private CarDAO carDAO;

    @Override
    public CarDTO save(CarDTO t) {
        return null;
    }

    @Override
    public CarDTO getById(Serializable id) {
        return null;
    }

    @Override
    public List<CarDTO> getAll() {
        return List.of();
    }

    @Override
    public CarDTO update(Serializable id, CarDTO car) throws IllegalAccessException {
        return null;
    }

    @Override
    public boolean delete(Serializable id) {
        return false;
    }
}
