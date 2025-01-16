package PetBridge.adoptionPost.exception;

import PetBridge.common.exception.BadRequestException;

public class AdoptionPostNotFoundException extends BadRequestException {
    public AdoptionPostNotFoundException() {
        super(AdoptionPostErrorType.POST_NOT_FOUND);//RuntimeException에 메시지를 전달해 예외 메시지를 저장할 수 있도록 함
    }
}
