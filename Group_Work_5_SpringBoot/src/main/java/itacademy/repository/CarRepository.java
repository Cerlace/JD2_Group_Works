package itacademy.repository;

import itacademy.entity.CarEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    Page<CarEntity> findByEngineId(Long engineId, Pageable pageable);
    Page<CarEntity> findByBrand(String brand, Pageable pageable);
}
