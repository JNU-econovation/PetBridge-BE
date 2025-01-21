package PetBridge.adoptionPost.exception;

import PetBridge.common.exception.BadRequestException;

public class AdoptionPostNotFoundException extends BadRequestException {
    public AdoptionPostNotFoundException() {
        super(AdoptionPostErrorType.ADOPTION_POST_NOT_FOUND_EXCEPTION);
    }
}
