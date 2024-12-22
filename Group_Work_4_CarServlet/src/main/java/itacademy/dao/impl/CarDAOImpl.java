package itacademy.dao.impl;

import itacademy.dao.CarDAO;
import itacademy.entity.CarEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

public class CarDAOImpl extends DAOImpl<CarEntity> implements CarDAO {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(CarDAOImpl.class);
    private final EntityManager em;

    public CarDAOImpl() {
        super(CarEntity.class, LOGGER);
        this.em = super.getEntityManager();
    }
}
