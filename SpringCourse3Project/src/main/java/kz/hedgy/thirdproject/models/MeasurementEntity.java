package kz.hedgy.thirdproject.models;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/*********************
 * @CREATED: 30.08.2022
 * @AUTHOR: Hedgy
 **********************/

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "measurements")
public class MeasurementEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @NotNull(message = "Поле значение показаний обязательно для заполнения")
    @Min(value = -100, message = "Значение поля Показания допускается в диапазоне от -100 до 100")
    @Max(value = 100, message = "Значение поля Показания допускается в диапазоне от -100 до 100")
    @Column(nullable = false)
    private Double value;

    @NotNull(message = "Поле значение Дождь обязательно для заполнения")
    @Column(nullable = false)
    private Boolean raining;

    @NotNull
    @Column(name = "measure_dt", nullable = false)
    private LocalDateTime measureDate;

    @NotNull(message = "не передано поле Наименование сенсора")
    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    @ToString.Exclude
    private SensorEntity sensor;


}
