package PetBridge.adoption.exception;

import PetBridge.common.exception.BadRequestException;

public class AlreadyFinalizationAdoptionException extends BadRequestException {
    public AlreadyFinalizationAdoptionException() {
        super(AdoptionErrorType.ALREADY_FINALIZATION_ADOPTION_EXCEPTION);
    }
}
