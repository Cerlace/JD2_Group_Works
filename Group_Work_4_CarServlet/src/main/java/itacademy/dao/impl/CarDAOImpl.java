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
    private static final String SAVE_LOG_MESSAGE = "Table: '{}', start saving object {}";
    private static final String UPDATE_LOG_MESSAGE = "Table: '{}', start updating row with id = {}";
    private static final String DELETE_LOG_MESSAGE = "Table: '{}', start deleting row with id = {}";
    private static final String GET_LOG_MESSAGE = "Table: '{}', start getting row with id = {}";
    private static final String GET_ALL_LOG_MESSAGE = "Table: '{}', start getting all rows";
    private static final String CLOSING_SESSION_MESSAGE = "Closing session";

    private final org.slf4j.Logger logger;
    private final CarEntity carEntity;
    private final EntityManager entityManager;
    private static final String TABLE_NAME = "cars";

    private static final Logger LOGGER =
            LoggerFactory.getLogger(CarDAOImpl.class);

    /**
     * В конструктор передается класс CarEntity
     *
     * @param carEntity объект класса, с которым работает DAO
     * @param logger    объект logger соответствующего класса
     */
    public CarDAOImpl(CarEntity carEntity, Logger logger) {
        this.carEntity = carEntity;
        this.entityManager = HibernateUtil.getEntityManager();
        this.logger = logger;
    }

    /**
     * Метод добавляет в таблицу запись
     *
     * @param carEntity объект, поля которого нужно записать в таблицу
     * @return этот же объект с полученным от БД идентификатором
     */
    @Override
    public CarEntity save(CarEntity carEntity) {
        logger.info(SAVE_LOG_MESSAGE, TABLE_NAME, carEntity);
        return ExecutorUtil.executeHibernate(this.entityManager, em -> {
            em.persist(carEntity);
            return carEntity;
        });
    }

    /**
     * Метод получает из таблицы все записи
     *
     * @return список объектов Entity, соответствующих таблице в БД
     */
    @Override
    public List<CarEntity> getAll() {
        logger.info(GET_ALL_LOG_MESSAGE, TABLE_NAME);
        String query = "FROM " + TABLE_NAME;
        return ExecutorUtil.executeHibernate(this.entityManager,
                em -> em.createQuery(query).getResultList());
    }

    /**
     * Метод обновляет поля в таблице у записи с указанным идентификатором
     *
     * @param id        уникальный идентификатор записи в таблице
     * @param carEntity объект CarEntity, соответствующий таблице, полями которого будут заменены
     *                  поля записи с идентификатором id
     */
    @Override
    public CarEntity update(Serializable id, CarEntity carEntity) {
        logger.info(UPDATE_LOG_MESSAGE, TABLE_NAME, id);
        return ExecutorUtil.executeHibernate(this.entityManager, em -> {
            CarEntity updatedEntity = em.merge(carEntity);
            return updatedEntity;
        });
    }

    /**
     * Метод удаляет из таблицы в БД запись с указанным идентификатором
     *
     * @param id уникальный идентификатор записи в таблице
     * @return количество удаленных записей
     */
    @Override
    public boolean delete(Serializable id) {
        logger.info(DELETE_LOG_MESSAGE, TABLE_NAME, id);

        return Boolean.TRUE.equals(ExecutorUtil.executeHibernate(this.entityManager, em -> {
            if (carEntity != null) {
                em.remove(carEntity);
                return true;
            } else {
                return false;
            }
        }));
    }

    @Override
    public void close() {
        logger.info(CLOSING_SESSION_MESSAGE);
        this.entityManager.close();
    }

}
