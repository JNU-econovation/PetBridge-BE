package PetBridge.common.exception;

import lombok.Getter;

@Getter
public class InternalServerException extends RuntimeException{
    private ErrorType errorType;

    public InternalServerException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
