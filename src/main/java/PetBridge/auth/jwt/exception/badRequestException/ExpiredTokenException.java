package PetBridge.auth.jwt.exception.badRequestException;

import PetBridge.auth.jwt.exception.TokenErrorType;
import PetBridge.common.exception.BadRequestException;

public class ExpiredTokenException extends BadRequestException {
    public ExpiredTokenException() {
        super(TokenErrorType.EXPIRED_TOKEN_EXCEPTION);
    }
}
