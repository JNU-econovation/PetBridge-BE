package PetBridge.common.exception;

import org.springframework.http.HttpStatus;

public enum CommonErrorType implements ErrorType{
    S3_IO_EXCEPTION("S3500_001", HttpStatus.BAD_REQUEST, "S3 예외 발생");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;

    CommonErrorType(String errorCode, HttpStatus httpStatus, String message) {
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
