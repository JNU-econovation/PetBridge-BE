package PetBridge.wish.exception;

import PetBridge.common.exception.BadRequestException;

public class NotFoundWishException extends BadRequestException {
    public NotFoundWishException() {
        super(WishErrorType.NOT_FOUND_WISH_EXCEPTION);
    }
}
