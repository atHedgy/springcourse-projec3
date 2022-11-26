package kz.hedgy.thirdproject.exception;

/*********************
 * @CREATED: 01.09.2022
 * @AUTHOR: Hedgy
 **********************/
public class NotFoundException extends RuntimeException {
    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
