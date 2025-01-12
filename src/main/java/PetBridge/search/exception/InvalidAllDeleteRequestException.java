package PetBridge.search.exception;

import PetBridge.common.exception.BadRequestException;

public class InvalidAllDeleteRequestException extends BadRequestException {
    public InvalidAllDeleteRequestException() {
        super(SearchErrorType.INVALID_ALL_DELETE_REQUEST_EXCEPTION);
    }
}
