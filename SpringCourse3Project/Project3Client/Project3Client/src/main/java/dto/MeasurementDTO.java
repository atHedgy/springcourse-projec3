package dto;

import lombok.Getter;
import lombok.Setter;

/*********************
 * @CREATED: 26.11.2022
 * @AUTHOR: Hedgy
 **********************/
@Getter
@Setter
public class MeasurementDTO {
    private Double value;
    private Boolean raining;
    private SensorDTO sensor;
}
