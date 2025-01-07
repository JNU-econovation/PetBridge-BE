package PetBridge.member.exception.BadRequest;

import PetBridge.common.exception.BadRequestException;
import PetBridge.member.exception.MemberErrorType;

public class InvalidEmailOrPasswordException extends BadRequestException {
    public InvalidEmailOrPasswordException() {
        super(MemberErrorType.INVALID_EMAIL_OR_PASSWORD_EXCEPTION);
    }
}
