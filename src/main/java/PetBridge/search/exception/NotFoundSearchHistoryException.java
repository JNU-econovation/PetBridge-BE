package PetBridge.search.exception;

import PetBridge.common.exception.BadRequestException;

public class NotFoundSearchHistoryException extends BadRequestException {
    public NotFoundSearchHistoryException() {
        super(SearchErrorType.NOT_FOUND_SEARCH_HISTORY_EXCEPTION);
    }
}
