package kz.hedgy.thirdproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*********************
 * @CREATED: 01.09.2022
 * @AUTHOR: Hedgy
 **********************/

@AllArgsConstructor
@Setter
@Getter
public class ErrorResponse {

    private HttpStatus status;
    private String message;
    private LocalDateTime timestamp;
    private List<ErrorDetail> errorList;

    private ErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

    private void addDetailError(ErrorDetail errorDetail) {
        if (errorList == null)
            errorList = new ArrayList<>();

        errorList.add(errorDetail);
    }

    public void addError (String field, String message) {
        addDetailError(new ErrorDetail(field, message));
    }

    private void addError(FieldError fieldError) {
        addError(fieldError.getField(),
                fieldError.getDefaultMessage());
    }

    public void addError(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addError);
    }

}
