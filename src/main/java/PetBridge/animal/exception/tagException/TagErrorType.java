package PetBridge.animal.exception.tagException;

import PetBridge.common.exception.ErrorType;
import org.springframework.http.HttpStatus;

public enum TagErrorType implements ErrorType {
    TAG_NOT_FOUNT_EXCEPTION("Tag404_001", HttpStatus.NOT_FOUND, "해당되는 태그를 찾을 수 없습니다");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;

    TagErrorType(String errorCode, HttpStatus httpStatus, String message) {
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
