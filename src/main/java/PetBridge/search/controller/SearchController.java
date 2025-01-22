package PetBridge.search.controller;

import PetBridge.auth.jwt.annotation.ValidMember;
import PetBridge.member.model.entity.Member;
import PetBridge.search.dto.SearchHistoryDTO;
import PetBridge.search.dto.res.RecentSearchHistoryRes;
import PetBridge.search.service.SearchConditionService;
import PetBridge.search.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/search")
public class SearchController {
    private final SearchService searchService;
    private final SearchConditionService searchConditionService;

    @GetMapping("/recently")
    public ResponseEntity<RecentSearchHistoryRes> getRecentSearchHistory (
            @ValidMember Member member
    ) {
        List<SearchHistoryDTO> historyDTOList = searchService.getRecentSearchHistory(member);
        return ResponseEntity.status(HttpStatus.OK).body(RecentSearchHistoryRes.from(historyDTOList));
    }

    @DeleteMapping("/{searchHistoryId}")
    public ResponseEntity<Void> removeSingleSearchHistory(
            @ValidMember Member member,
            @PathVariable(name = "searchHistoryId", required = false) Long searchHistoryId
    ) {
        searchService.deleteSingleHistory(member, searchHistoryId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @DeleteMapping
    public ResponseEntity<Void> removeAllSearchHistory(
            @ValidMember Member member,
            @RequestParam(name = "all") Boolean isRemoveAll
    ) {
        searchService.deleteAllHistory(member, isRemoveAll);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping
    public ResponseEntity<Void> resetSearchCondition(
            @ValidMember Member member
    ) {
        searchConditionService.resetSearchCondition(member);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
