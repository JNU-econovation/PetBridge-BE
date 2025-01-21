package PetBridge.animal.exception.breedException;

import PetBridge.common.exception.ErrorType;
import org.springframework.http.HttpStatus;

public enum BreedErrorType implements ErrorType {
    BREED_NOT_FOUND_EXCEPTION("Breed404_001", HttpStatus.NOT_FOUND, "해당 되는 품종을 찾을 수 없습니다");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;

    BreedErrorType(String errorCode, HttpStatus httpStatus, String message) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }


    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
