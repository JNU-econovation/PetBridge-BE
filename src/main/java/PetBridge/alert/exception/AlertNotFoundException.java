package PetBridge.alert.exception;

import PetBridge.common.exception.BadRequestException;

public class AlertNotFoundException extends BadRequestException {
    public AlertNotFoundException() {
        super(AlertErrorType.ALERT_NOT_FOUND_EXCEPTION);
    }
}
