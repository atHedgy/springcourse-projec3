package kz.hedgy.thirdproject.exception;

import lombok.Getter;
import lombok.Setter;

/*********************
 * @CREATED: 26.10.2022
 * @AUTHOR: Hedgy
 **********************/
@Setter
@Getter
public class ErrorDetail {
    private String field;
    private String message;

    ErrorDetail(String object, String message) {
        this.field = object;
        this.message = message;
    }

}
