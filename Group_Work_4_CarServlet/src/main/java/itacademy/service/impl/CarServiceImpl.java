package itacademy.service.impl;

import itacademy.dao.CarDAO;
import itacademy.dao.impl.CarDAOImpl;
import itacademy.dto.CarDTO;
import itacademy.entity.CarEntity;
import itacademy.service.CarService;
import itacademy.utils.CarConverter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImpl implements CarService {
    private final CarDAO carDAO = new CarDAOImpl();

    @Override
    public CarDTO save(CarDTO carDTO) {
        CarEntity carEntity = carDAO.save(CarConverter.toEntity(carDTO));
        carDTO.setId(carEntity.getId());
        return carDTO;
    }

    @Override
    public List<CarDTO> getAll() {
        List<CarEntity> cars = carDAO.getAll();

        return cars.stream()
                .map(CarConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CarDTO update(Serializable id, CarDTO carDTO) {
        Integer intId = Integer.parseInt((String) id);
        CarEntity carEntity = carDAO.update(intId,
                CarConverter.toEntity(carDTO));
        if (carEntity != null) {
            carDTO.setId(carEntity.getId());
        }

        return carDTO;
    }

    @Override
    public boolean delete(Serializable id) {
        return carDAO.delete(Integer.parseInt((String) id));
    }

    @Override
    public void closeDao() {
        this.carDAO.close();
    }
}
