package PetBridge.auth.jwt.exception.badRequestException;

import PetBridge.auth.jwt.exception.TokenErrorType;
import PetBridge.common.exception.BadRequestException;

public class NullTokenException extends BadRequestException {
    public NullTokenException() {
        super(TokenErrorType.NULL_TOKEN_EXCEPTION);
    }
}
