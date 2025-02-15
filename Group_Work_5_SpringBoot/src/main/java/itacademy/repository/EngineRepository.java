package itacademy.repository;

import itacademy.entity.EngineEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineRepository extends JpaRepository<EngineEntity, Long> {
    Page<EngineEntity> findByHorsePowerGreaterThan(Integer horsePower, Pageable pageable);
}
