package PetBridge.search.dto;

import PetBridge.search.model.entity.SearchHistory;

public record SearchHistoryDTO (
        Long id,
        String keyword
) {
    public static SearchHistoryDTO from(SearchHistory searchHistory) {
        return new SearchHistoryDTO(
                searchHistory.getId(),
                searchHistory.getKeyword());
    }
}
