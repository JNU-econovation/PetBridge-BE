package PetBridge.adoptionPost.exception;

import PetBridge.common.exception.BadRequestException;

public class TagAdoptionPostMappingNotFoundException extends BadRequestException {
    public TagAdoptionPostMappingNotFoundException() {
        super(TagAdoptionPostMappingErrorType.TAG_ADOPTION_POST_MAPPING_NOT_FOUND_EXCEPTION);
    }
}
