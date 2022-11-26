package kz.hedgy.thirdproject.utils;

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
public class SensorValidator implements Validator {
    private final SensorService sensorService;

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorEntity sensor = (SensorEntity) target;
        if (sensorService.findByName(sensor.getName()).isPresent())
            errors.rejectValue("name", null, "Сенсор с именем("+ sensor.getName() +") уже зарегистрирован!");
    }
}
