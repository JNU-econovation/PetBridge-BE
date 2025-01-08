package PetBridge.auth.jwt.exception;

import PetBridge.common.exception.ErrorType;
import org.springframework.http.HttpStatus;

public enum TokenErrorType implements ErrorType {
    NULL_TOKEN_EXCEPTION ("Auth400_001", HttpStatus.BAD_REQUEST, "토큰 값이 null 입니다"),
    EXPIRED_TOKEN_EXCEPTION ("Auth400_002", HttpStatus.BAD_REQUEST, "만료된 토큰 입니다"),
    NOT_VALID_TOKEN_EXCEPTION ("Auth400_003", HttpStatus.BAD_REQUEST, "토큰값이 잘못되었습니다"),
    REFRESH_TOKEN_NOT_FOUND_EXCEPTION("Auth400_004", HttpStatus.BAD_REQUEST, "존재하지 않는 리프레쉬 토큰입니다");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;

    TokenErrorType(String errorCode, HttpStatus httpStatus, String message) {
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
