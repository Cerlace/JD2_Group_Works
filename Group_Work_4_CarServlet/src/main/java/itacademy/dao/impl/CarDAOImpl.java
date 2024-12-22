package itacademy.dao.impl;

import itacademy.dao.CarDAO;
import itacademy.entity.CarEntity;
import itacademy.utils.ExecutorUtil;
import itacademy.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class CarDAOImpl implements CarDAO {
    private final EntityManager em;

    public CarDAOImpl() {
        this.em = HibernateUtil.getEntityManager();
    }

    @Override
    public CarEntity save(CarEntity carEntity) {
        return ExecutorUtil.executeHibernate(this.em, em -> {
            em.persist(carEntity);
            return carEntity;
        });
    }

    @Override
    public List<CarEntity> getAll() {
        Query query = em.createQuery("SELECT c FROM CarEntity c");
        return ExecutorUtil.executeHibernate(this.em, em ->
                (List<CarEntity>) query.getResultList());
    }

    @Override
    public CarEntity update(Serializable id, CarEntity carEntity) {
        return ExecutorUtil.executeHibernate(this.em, em -> {
            CarEntity updatedCar = this.em.find(CarEntity.class, id);
            if (updatedCar != null) {
                updatedCar = em.merge(carEntity);
            }

            return updatedCar;
        });
    }

    @Override
    public boolean delete(Serializable id) {
        return Boolean.TRUE.equals(ExecutorUtil.executeHibernate(this.em, em -> {
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
        if (this.em.isOpen()) {
            this.em.close();
        }
    }
}

