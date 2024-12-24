package itacademy.dao.impl;

import itacademy.dao.CarDAO;
import itacademy.entity.CarEntity;
import itacademy.utils.ExecutorUtil;
import itacademy.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class CarDAOImpl implements CarDAO {
    private static final String SAVE_LOG_MESSAGE = "start saving object {}";
    private static final String UPDATE_LOG_MESSAGE = "start updating row with id = {}";
    private static final String DELETE_LOG_MESSAGE = "start deleting row with id = {}";
    private static final String GET_ALL_LOG_MESSAGE = "start getting all cars";
    private static final String CLOSING_SESSION_MESSAGE = "Closing session";

    private final Logger LOGGER = LoggerFactory.getLogger(CarDAOImpl.class);
    private final EntityManager entityManager;

    public CarDAOImpl() {
        this.entityManager = HibernateUtil.getEntityManager();
    }

    @Override
    public CarEntity save(CarEntity carEntity) {
        LOGGER.info(SAVE_LOG_MESSAGE, carEntity);
        return ExecutorUtil.executeHibernate(this.entityManager, em -> {
            em.persist(carEntity);
            return carEntity;
        });
    }

    @Override
    public List<CarEntity> getAll() {
        LOGGER.info(GET_ALL_LOG_MESSAGE);
        String query = "FROM " + CarEntity.class.getSimpleName();
        return ExecutorUtil.executeHibernate(this.entityManager,
                em -> em.createQuery(query).getResultList());
    }

    @Override
    public CarEntity update(Serializable id, CarEntity carEntity) {
        LOGGER.info(UPDATE_LOG_MESSAGE, id);
        return ExecutorUtil.executeHibernate(this.entityManager, em -> {
            CarEntity updatedCar = this.entityManager.find(CarEntity.class, id);
            if (updatedCar != null) {
                updatedCar = em.merge(carEntity);
            }

            return updatedCar;
        });
    }

    @Override
    public boolean delete(Serializable id) {
        LOGGER.info(DELETE_LOG_MESSAGE, id);

        return Boolean.TRUE.equals(ExecutorUtil.executeHibernate(this.entityManager, em -> {
            CarEntity car = em.find(CarEntity.class, id);
            if (car != null) {
                em.remove(car);
                return true;
            } else {
                return false;
            }
        }));
    }

    @Override
    public void close() {
        if (this.entityManager.isOpen()) {
            LOGGER.info(CLOSING_SESSION_MESSAGE);
            this.entityManager.close();
        }
    }
}
