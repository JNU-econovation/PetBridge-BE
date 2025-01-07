package PetBridge.auth.exception.businessException;

import PetBridge.auth.exception.AuthErrorType;
import PetBridge.common.exception.BadRequestException;

public class AlreadySignedEmailException extends BadRequestException {
    public AlreadySignedEmailException() {
        super(AuthErrorType.ALREADY_SIGNED_EMAIL_EXCEPTION);
    }
}
