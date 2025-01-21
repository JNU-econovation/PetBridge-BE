package PetBridge.animal.exception.tagException.badRequestException;

import PetBridge.animal.exception.tagException.TagErrorType;
import PetBridge.common.exception.BadRequestException;

public class TagNotFoundException extends BadRequestException {
    public TagNotFoundException() {
        super(TagErrorType.TAG_NOT_FOUNT_EXCEPTION);
    }
}
