package PetBridge.auth.jwt.exception.badRequestException;

import PetBridge.auth.jwt.exception.TokenErrorType;
import PetBridge.common.exception.BadRequestException;

public class NotValidTokenException extends BadRequestException {
    public NotValidTokenException() {
        super(TokenErrorType.NOT_VALID_TOKEN_EXCEPTION);
    }
}
