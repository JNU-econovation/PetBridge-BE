package PetBridge.search.exception;

import PetBridge.common.exception.ErrorType;
import org.springframework.http.HttpStatus;

public enum SearchConditionErrorType implements ErrorType {
    SEARCH_CONDITION_NOT_FOUND_EXCEPTION("SearchCondition404_001", HttpStatus.NOT_FOUND, "해당하는 멤버의 검색 조건이 존재하지 않습니다.");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;

    SearchConditionErrorType(String errorCode, HttpStatus httpStatus, String message) {
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
