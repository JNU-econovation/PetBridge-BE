package PetBridge.adoption.exception;

import PetBridge.common.exception.BadRequestException;

public class AdoptionNotFoundException extends BadRequestException {
    public AdoptionNotFoundException() {
        super(AdoptionErrorType.ADOPTION_NOT_FOUND_EXCEPTION);
    }
}
