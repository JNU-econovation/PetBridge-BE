package PetBridge.adoptionPost.exception;

import PetBridge.common.exception.ErrorType;
import org.springframework.http.HttpStatus;

public enum TagAdoptionPostMappingErrorType implements ErrorType {
    TAG_ADOPTION_POST_MAPPING_NOT_FOUND_EXCEPTION("TagAdoptionPostMapping404_001", HttpStatus.NOT_FOUND, "해당 분양글에 달린 태그를 찾을 수 없습니다");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;

    TagAdoptionPostMappingErrorType(String errorCode, HttpStatus httpStatus, String message) {
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