package kz.hedgy.thirdproject.utils;

import kz.hedgy.thirdproject.models.MeasurementEntity;
import kz.hedgy.thirdproject.models.SensorEntity;
import kz.hedgy.thirdproject.services.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/*********************
 * @CREATED: 01.09.2022
 * @AUTHOR: Hedgy
 **********************/

@Component
@RequiredArgsConstructor
public class MeasurementValidator implements Validator {
    private final SensorService sensorService;

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementEntity measurement = (MeasurementEntity) target;

        if (measurement.getSensor() == null)
            //errors.rejectValue("sensor",  );
            return;

        if (sensorService.findByName(measurement.getSensor().getName()).isEmpty())
            errors.rejectValue("sensor", null,  String.format("Сенсор с именем = %s не зарегистрирован!", measurement.getSensor()));
    }
}
