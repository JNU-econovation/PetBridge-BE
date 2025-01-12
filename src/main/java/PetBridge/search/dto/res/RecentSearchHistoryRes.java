package PetBridge.search.dto.res;

import PetBridge.search.dto.SearchHistoryDTO;

import java.util.List;

public record RecentSearchHistoryRes (
        List<SearchHistoryDTO> SearchHistoryList
){
    public static RecentSearchHistoryRes from(List<SearchHistoryDTO> searchHistoryDTOList) {
        return new RecentSearchHistoryRes(searchHistoryDTOList);
    }
}
