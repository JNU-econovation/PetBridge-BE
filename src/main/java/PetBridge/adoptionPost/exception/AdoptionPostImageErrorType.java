package PetBridge.adoptionPost.exception;

import PetBridge.common.exception.ErrorType;
import org.springframework.http.HttpStatus;

public enum AdoptionPostImageErrorType implements ErrorType {
    ADOPTION_POST_IMAGE_NOT_FOUNT_EXCEPTION("AdoptionPostImage404_001", HttpStatus.NOT_FOUND, "해당하는 AdoptionPostImage를 찾을 수 없습니다");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;

    AdoptionPostImageErrorType(String errorCode, HttpStatus httpStatus, String message) {
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
