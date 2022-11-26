package kz.hedgy.thirdproject.utils;

import kz.hedgy.thirdproject.exception.CreateErrorException;
import kz.hedgy.thirdproject.exception.ErrorResponse;
import kz.hedgy.thirdproject.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/*********************
 * @CREATED: 01.09.2022
 * @AUTHOR: Hedgy
 **********************/

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleValidationException (MethodArgumentNotValidException exception) {
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, "Ошибка валидации");
        response.addError(exception.getBindingResult().getFieldErrors());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(response, response.getStatus()); // 404 status
    }

    @ExceptionHandler(CreateErrorException.class)
    private ResponseEntity<ErrorResponse> handleSensorCreateException(CreateErrorException ex) {
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(response, response.getStatus()); // 404 status
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleErrorException (Exception ex, WebRequest request) {
        ErrorResponse response = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(response, response.getStatus()); // 500 status
    }
}
