package PetBridge.search.exception;

import PetBridge.common.exception.BadRequestException;

public class SearchConditionNotFoundException extends BadRequestException {
    public SearchConditionNotFoundException() {
        super(SearchConditionErrorType.SEARCH_CONDITION_NOT_FOUND_EXCEPTION);
    }
}
