package PetBridge.email.exception;


import PetBridge.common.exception.InternalServerException;

public class FailToSendEmailCode extends InternalServerException {
    public FailToSendEmailCode() {
        super(EmailErrorType.FAIL_TO_SEND_EMAIL_CODE);
    }
}
