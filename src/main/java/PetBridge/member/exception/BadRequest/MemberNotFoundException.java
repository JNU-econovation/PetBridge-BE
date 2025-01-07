package PetBridge.member.exception.BadRequest;

import PetBridge.common.exception.BadRequestException;
import PetBridge.member.exception.MemberErrorType;

public class MemberNotFoundException extends BadRequestException {
    public MemberNotFoundException() {
        super(MemberErrorType.MEMBER_NOT_FOUND_EXCEPTION);
    }
}
