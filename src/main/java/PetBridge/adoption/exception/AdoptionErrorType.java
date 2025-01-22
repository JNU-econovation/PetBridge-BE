package PetBridge.adoption.exception;

import PetBridge.common.exception.ErrorType;
import org.springframework.http.HttpStatus;

public enum AdoptionErrorType implements ErrorType {
    ADOPTION_NOT_FOUND_EXCEPTION("Adoption404_001", HttpStatus.NOT_FOUND, "존재하지 않은 입양 입니다"),
    ALREADY_IN_PROGRESS_ADOPTION_EXCEPTION("Adoption400_001", HttpStatus.BAD_REQUEST, "이미 입양이 진행중인 입양글 입니다"),
    ALREADY_FINALIZATION_ADOPTION_EXCEPTION("Adoption400_002", HttpStatus.BAD_REQUEST, "이미 입양이 완료되었습니다");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;

    AdoptionErrorType(String errorCode, HttpStatus httpStatus, String message) {
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
