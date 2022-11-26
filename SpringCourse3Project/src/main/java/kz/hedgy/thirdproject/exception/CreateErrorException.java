package kz.hedgy.thirdproject.exception;

/*********************
 * @CREATED: 01.09.2022
 * @AUTHOR: Hedgy
 **********************/
public class CreateErrorException extends RuntimeException{
    public CreateErrorException(String message) {
        super(message);
    }
}
