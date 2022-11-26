package kz.hedgy.thirdproject.services;

import kz.hedgy.thirdproject.exception.CreateErrorException;
import kz.hedgy.thirdproject.models.MeasurementEntity;
import kz.hedgy.thirdproject.repositories.MeasurementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/*********************
 * @CREATED: 01.09.2022
 * @AUTHOR: Hedgy
 **********************/
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    public List<MeasurementEntity> getAll() {
        return measurementRepository.findAll();
    }

    public int getRainingCount() {
        return measurementRepository.countByRaining(true).orElse(null);
    }

    @Transactional
    public void addMeasuring(MeasurementEntity measurement) {
        try {
            setAdditionalInfo(measurement);
            measurementRepository.save(measurement);
        } catch (Exception e) {
            throw new CreateErrorException(e.getMessage());
        }
    }

    private void setAdditionalInfo(MeasurementEntity measurement) {
        measurement.setMeasureDate(LocalDateTime.now());
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()).get());
    }

    public void test() {
        System.out.println("Testing and debug");
    }
}
