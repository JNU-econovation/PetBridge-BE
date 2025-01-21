package PetBridge.animal.exception.breedException.badRequestException;

import PetBridge.animal.exception.breedException.BreedErrorType;
import PetBridge.common.exception.BadRequestException;

public class BreedNotFoundException extends BadRequestException {
    public BreedNotFoundException() {
        super(BreedErrorType.BREED_NOT_FOUND_EXCEPTION);
    }
}
