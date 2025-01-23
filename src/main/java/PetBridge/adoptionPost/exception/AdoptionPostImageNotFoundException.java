package PetBridge.adoptionPost.exception;

import PetBridge.common.exception.BadRequestException;

public class AdoptionPostImageNotFoundException extends BadRequestException {
    public AdoptionPostImageNotFoundException() {
        super(AdoptionPostImageErrorType.ADOPTION_POST_IMAGE_NOT_FOUNT_EXCEPTION);
    }
}
