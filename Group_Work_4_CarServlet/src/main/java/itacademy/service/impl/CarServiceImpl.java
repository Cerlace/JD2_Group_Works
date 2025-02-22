package itacademy.service.impl;

import itacademy.dao.CarDAO;
import itacademy.dao.impl.CarDAOImpl;
import itacademy.dto.CarDTO;
import itacademy.entity.CarEntity;
import itacademy.service.CarService;
import itacademy.utils.ConverterUtil;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImpl implements CarService {
    private final CarDAO carDAO = new CarDAOImpl();

    @Override
    public CarDTO save(CarDTO carDTO) {
        CarEntity carEntity = ConverterUtil.convertCar(carDTO);
        carEntity.setCreationTime(new Timestamp(System.currentTimeMillis()));
        carEntity.setChangeTime(new Timestamp(System.currentTimeMillis()));

        carDTO.setId(carDAO.save(carEntity).getId());

        return carDTO;
    }

    @Override
    public CarDTO get(Integer id) {
        return ConverterUtil.convertCar(carDAO.get(id));
    }

    @Override
    public List<CarDTO> getAll() {
        return carDAO.getAll().stream()
                .map(ConverterUtil::convertCar)
                .collect(Collectors.toList());
    }

    @Override
    public CarDTO update(Integer id, CarDTO carDTO) {
        CarEntity carEntity = ConverterUtil.convertCar(carDTO);
        carEntity.setChangeTime(new Timestamp(System.currentTimeMillis()));
        carEntity.setId(id);
        carDTO.setId(carDAO.update(id, carEntity).getId());

        return carDTO;
    }

    @Override
    public boolean delete(Integer id) {
        return carDAO.delete(id);
    }

    @Override
    public void closeDao() {
        this.carDAO.close();
    }
}
