package itacademy.service.impl;

import itacademy.dao.CarDAO;
import itacademy.dao.impl.CarDAOImpl;
import itacademy.dto.CarDTO;
import itacademy.entity.CarEntity;
import itacademy.service.CarService;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImpl implements CarService {
    private final CarDAO carDAO = new CarDAOImpl();

    @Override
    public CarDTO save(CarDTO carDTO) {
        CarEntity carEntity = carDAO.save(CarEntity.builder()
                .vin(carDTO.getVin())
                .name(carDTO.getName())
                .changeTime(new Timestamp(System.currentTimeMillis()))
                .build());
        carDTO.setId(carEntity.getId());

        return carDTO;
    }

    @Override
    public List<CarDTO> getAll() {
        List<CarEntity> cars = carDAO.getAll();

        return cars.stream()
                .map(carEntity ->
                        CarDTO.builder()
                                .id(carEntity.getId())
                                .vin(carEntity.getVin())
                                .name(carEntity.getName())
                                .build()).collect(Collectors.toList());
    }

    @Override
    public CarDTO update(Serializable id, CarDTO carDTO) {
        Integer intId = Integer.parseInt((String) id);
        CarEntity carEntity = carDAO.update(intId,
                CarEntity.builder()
                        .id(intId)
                        .vin(carDTO.getVin())
                        .name(carDTO.getName())
                        .changeTime(new Timestamp(System.currentTimeMillis()))
                        .build());

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
