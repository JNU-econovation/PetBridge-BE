package PetBridge.adoption.exception;

import PetBridge.common.exception.BadRequestException;

public class AlreadyInProgressAdoptionException extends BadRequestException {
    public AlreadyInProgressAdoptionException() {
        super(AdoptionErrorType.ALREADY_IN_PROGRESS_ADOPTION_EXCEPTION);
    }
}
