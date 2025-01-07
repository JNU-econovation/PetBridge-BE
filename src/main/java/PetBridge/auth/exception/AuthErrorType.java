package PetBridge.auth.exception;

import PetBridge.common.exception.ErrorType;
import org.springframework.http.HttpStatus;

public enum AuthErrorType implements ErrorType {
    ALREADY_SIGNED_EMAIL_EXCEPTION("Auth400_001",HttpStatus.BAD_REQUEST, "이미 가입된 이메일 입니다");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;

    AuthErrorType(String errorCode, HttpStatus httpStatus, String message) {
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
