package PetBridge.email.exception;

import PetBridge.common.exception.BadRequestException;
import PetBridge.common.exception.ErrorType;

public class EmailNotFoundException extends BadRequestException {
    public EmailNotFoundException() {
        super(EmailErrorType.EMAIL_NOT_FOUND_EXCEPTION);
    }
}
