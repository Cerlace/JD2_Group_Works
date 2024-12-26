package itacademy.service;

import itacademy.dto.CarDTO;

import java.io.Serializable;
import java.util.List;

public interface CarService {
    /**
     * Получает из сервлета DTO, конвертирует в Entity
     * и передает на слой DAO для сохранения
     * @param t DTO, который нужно записать в БД
     * @return записанный в БД объект с id
     */
    CarDTO save(CarDTO t);

    /**
     * Получает из слоя DAO список всех объектов Entity в БД,
     * конвертирует их в объекты DTO для дальнейшей передачи сервлету
     * @return список объектов DTO
     */
    List<CarDTO> getAll();

    /**
     * Получает из сервлета DTO и id, конвертирует DTO в Entity
     * и передает на слой DAO для обновления записи в БД
     * @param car DTO, который нужно обновить в БД
     * @param id идентификатор записи в БД
     * @return обновленный в БД объект
     */
    CarDTO update(Integer id, CarDTO car);

    /**
     * С помощью слоя DAO инициирует удаление объекта из БД
     * @param id идентификатор записи для удаления
     * @return true, если запись удалена, false, если запись не была найдена
     * или не произошло удаление
     */
    boolean delete(Integer id);

    /**
     * Закрывает сессию у объекта DAO
     */
    void closeDao();
}
