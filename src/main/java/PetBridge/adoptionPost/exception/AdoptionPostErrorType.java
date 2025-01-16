package PetBridge.adoptionPost.exception;

import PetBridge.common.exception.ErrorType;
import org.springframework.http.HttpStatus;

public enum AdoptionPostErrorType implements ErrorType {
    POST_NOT_FOUND("AdoptionPost404_001", HttpStatus.NOT_FOUND, "해당 ID의 분양글을 찾을 수 없습니다."),
    POST_CREATED("AdoptionPost201_001", HttpStatus.CREATED, "분양글이 성공적으로 생성되었습니다."),
    POST_UPDATED("AdoptionPost200_001", HttpStatus.OK, "분양글이 성공적으로 수정되었습니다."),
    POST_DELETED("AdoptionPost200_002", HttpStatus.OK, "분양글이 성공적으로 삭제되었습니다."),
    POST_FOUND("AdoptionPost200_003", HttpStatus.OK, "분양글이 성공적으로 조회되었습니다."),
    POST_SORTED("AdoptionPost200_004", HttpStatus.OK, "분양글이 성공적으로 정렬되었습니다.");

    private final String errorCode; // 에러 코드 (사용자 정의 코드)
    private final HttpStatus httpStatus; // HTTP 상태 코드
    private final String message; // 응답메시지 (응답에 포함될 메시지)

    AdoptionPostErrorType(String errorCode, HttpStatus httpStatus, String message) {
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
