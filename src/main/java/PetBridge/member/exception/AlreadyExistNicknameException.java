package PetBridge.member.exception;

import PetBridge.common.exception.BadRequestException;

public class AlreadyExistNicknameException extends BadRequestException {
    public AlreadyExistNicknameException() {
        super(MemberErrorType.ALREADY_EXIST_NICKNANE_EXCEPTION);
    }
}
