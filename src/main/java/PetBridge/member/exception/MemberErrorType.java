package PetBridge.member.exception;

import PetBridge.common.exception.ErrorType;
import org.springframework.http.HttpStatus;

public enum MemberErrorType implements ErrorType {
    MEMBER_NOT_FOUND_EXCEPTION ("Member404_001", HttpStatus.NOT_FOUND, "해당하는 멤버를 찾을 수 없습니다"),
    INVALID_EMAIL_OR_PASSWORD_EXCEPTION("Member400_001",HttpStatus.BAD_REQUEST, "이메일이나 비밀번호가 틀렸습니다");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;

    MemberErrorType(String errorCode, HttpStatus httpStatus, String message) {
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
