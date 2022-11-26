package kz.hedgy.thirdproject.services;

import kz.hedgy.thirdproject.exception.CreateErrorException;
import kz.hedgy.thirdproject.exception.NotFoundException;
import kz.hedgy.thirdproject.models.SensorEntity;
import kz.hedgy.thirdproject.repositories.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/*********************
 * @CREATED: 31.08.2022
 * @AUTHOR: Hedgy
 **********************/
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SensorService {
    private final SensorRepository sensorRepository;

    public Optional<SensorEntity> findByName (String sensorName) {
        Optional<SensorEntity> sensor = sensorRepository.findByName(sensorName);
        if (sensor == null)
            throw new NotFoundException("Сенсор с наименованием "+ sensorName +" не найден!");
        return sensor;
    }

    private void setAdditionalInfo(SensorEntity sensor) {
        sensor.setCreateDate(LocalDateTime.now());
        sensor.setUsername("SENSOR_1_USER");
    }

    public List<SensorEntity> getAll() {
        return sensorRepository.findAll();
    }

    @Transactional
    public void registerSensor(SensorEntity sensor) {
        try {
            setAdditionalInfo(sensor);
            sensorRepository.save(sensor);
        } catch (Exception e) {
            throw new CreateErrorException(e.getMessage());
        }
    }

    public void test() {
        System.out.println("Testing and debug");
    }
}
