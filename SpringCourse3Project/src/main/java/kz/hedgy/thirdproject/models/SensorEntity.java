package kz.hedgy.thirdproject.models;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @CREATED: 29.08.2022
 * @AUTHOR: Hedgy
*/

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "sensors")
public class SensorEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @NotEmpty(message = "Наименование сенсора должно быть заполнено!")
    @Size(min = 2, max = 30, message = "Наименование сенсора должно содержать от 2 до 30 символов")
    @Column(nullable = false)
    private String name;

    @Column(name = "create_dt", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "change_dt", nullable = false)
    private LocalDateTime changeDate;

    @Column(name = "user_name", nullable = false)
    private String username;

}
