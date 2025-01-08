package PetBridge.common.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException{
    private ErrorType errorType;

    public BadRequestException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
