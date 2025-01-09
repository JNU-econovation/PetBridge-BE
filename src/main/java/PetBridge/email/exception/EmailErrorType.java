package PetBridge.email.exception;

import PetBridge.common.exception.ErrorType;
import org.springframework.http.HttpStatus;

public enum EmailErrorType implements ErrorType {
    EMAIL_NOT_FOUND_EXCEPTION("Email404_001", HttpStatus.NOT_FOUND, "일치하는 이메일을 찾을 수 없습니다"),
    FAIL_TO_SEND_EMAIL_CODE("Email500_001", HttpStatus.INTERNAL_SERVER_ERROR, "이메일 코드 전송 실패");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;

    EmailErrorType(String errorCode, HttpStatus httpStatus, String message) {
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
