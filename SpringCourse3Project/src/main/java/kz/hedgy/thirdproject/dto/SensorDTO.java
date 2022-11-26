package kz.hedgy.thirdproject.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/*********************
 * @CREATED: 02.09.2022
 * @AUTHOR: Hedgy
 **********************/
@Getter
@Setter
public class SensorDTO {
    @NotEmpty(message = "Наименование сенсора должно быть заполнено!")
    @Size(min = 2, max = 30, message = "Наименование сенсора должно содержать от 2 до 30 символов")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
