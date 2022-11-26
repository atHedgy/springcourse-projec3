package kz.hedgy.thirdproject.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/*********************
 * @CREATED: 02.09.2022
 * @AUTHOR: Hedgy
 **********************/
@Getter
@Setter
public class MeasurementDTO {
    @NotNull(message = "Значение поля Показания обязательно для заполнения")
    @Min(value = -100, message = "Значение поля Показания допускается в диапазоне от -100 до 100")
    @Max(value = 100, message = "Значение поля Показания допускается в диапазоне от -100 до 100")
    private Double value;

    @NotNull(message = "Значение поля Дождь обязательно для заполнения (true,false)")
    private Boolean raining;

    @NotNull(message = "не передано поле Наименование сенсора")
    private SensorDTO sensor;
}
