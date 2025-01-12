package PetBridge.search.exception;

import PetBridge.common.exception.ErrorType;
import org.springframework.http.HttpStatus;

public enum SearchErrorType implements ErrorType {
    NOT_FOUND_SEARCH_HISTORY_EXCEPTION("Search404_001", HttpStatus.NOT_FOUND, "해당 유저의 검색기록이 없습니다"),
    INVALID_ALL_DELETE_REQUEST_EXCEPTION("Search400_001",HttpStatus.BAD_REQUEST ,"검색기록 일괄 삭제시 all 옵션은 true여야 합니다" );

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;

    SearchErrorType(String errorCode, HttpStatus httpStatus, String message) {
        this.errorCode =errorCode;
        this.httpStatus =httpStatus;
        this.message =message;
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
