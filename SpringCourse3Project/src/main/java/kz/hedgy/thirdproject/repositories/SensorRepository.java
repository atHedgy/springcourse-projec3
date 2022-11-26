package kz.hedgy.thirdproject.repositories;

import kz.hedgy.thirdproject.models.SensorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*********************
 * @CREATED: 31.08.2022
 * @AUTHOR: Hedgy
 **********************/
@Repository
public interface SensorRepository extends JpaRepository<SensorEntity, Integer> {
    Optional<SensorEntity> findByName(String sensorName);
}
