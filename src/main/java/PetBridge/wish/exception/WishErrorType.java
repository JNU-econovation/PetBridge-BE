package PetBridge.wish.exception;

import PetBridge.common.exception.ErrorType;
import org.springframework.http.HttpStatus;

public enum WishErrorType implements ErrorType {
    NOT_FOUND_WISH_EXCEPTION("Wish404_001", HttpStatus.NOT_FOUND, "존재하는 좋아요가 없습니다"),
    DUPLICATE_WISH_EXCEPTION("Wish400_001", HttpStatus.BAD_REQUEST, "이미 좋아요가 눌린 게시글입니다");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;

    WishErrorType(String errorCode, HttpStatus httpStatus, String message) {
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
